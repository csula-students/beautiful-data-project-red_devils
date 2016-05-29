package edu.csula.datascience.acquisition;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TrafficViolationSource implements Source<TrafficViolation> {

	private static int count = 0;
	private static List<TrafficViolation> list = new ArrayList<TrafficViolation>();

	public static void main(String args[]) throws IOException,
			URISyntaxException {

		getAPIData(list);
		System.out.println("finish calling api call and collect data");
		
		File file = new File(ClassLoader.getSystemResource("Traffic_Violations.csv").toURI());
		readData(file, list);
		System.out.println("finished read and collect data from CSV");

	}

	public static void getAPIData(List<TrafficViolation> list)
			throws IOException, URISyntaxException {
		System.out.println("Started getting data from api");
		BufferedReader bf = null;
		String link_url = "https://data.montgomerycountymd.gov/resource/ms8i-8ux3.json?$$app_token="+AccessToken.accessToken+"&$limit=1000000";
		// link_url="https://data.montgomerycountymd.gov/resource/ms8i-8ux3.json?$$app_token="+AccessToken.accessToken+"&$limit=1000000";
		URL url = new URL(link_url);
		HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
		bf = new BufferedReader(new InputStreamReader(con.getInputStream()));
		ObjectMapper objectmapper = new ObjectMapper();
		String line = null;
		while ((line = bf.readLine()) != null) {
			try {
				if (line.substring(0, 1).contains("["))
					line = line.substring(1);
				else if (line.substring(0, 1).contains(","))
					line = line.substring(1);
				TrafficViolation tvobj = objectmapper.readValue(line,
						TrafficViolation.class);
				list.add(tvobj);
				if (list.size() == 50000) {
					TrafficViolationCollector c = new TrafficViolationCollector();
					c.mungee(list);
					c.save(list);
					c.saveElasticsearch(list);
					System.out.println("50000 records are inserted in mongo");
					count = 0;
					list = new ArrayList<TrafficViolation>();
				}
				count++;
				System.out.println(count);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		TrafficViolationCollector c = new TrafficViolationCollector();
		c.mungee(list);
		c.save(list);
		c.saveElasticsearch(list);
		list = new ArrayList<TrafficViolation>();
		System.out.println("Total " + count + " inserted");
	}

	public static void readData(File descriptionCsvFile,
			List<TrafficViolation> list) throws URISyntaxException {
		CSVParser parser;
		try {
			parser = CSVParser.parse(descriptionCsvFile,
					Charset.defaultCharset(), CSVFormat.EXCEL.withHeader());
			for (CSVRecord csvRecord : parser) {
				try {
					DateFormat userDateFormat = new SimpleDateFormat("MM/dd/yyyy");
					 DateFormat dateFormatNeeded = new SimpleDateFormat("yyyy-MM-dd");
					 Date dategot = userDateFormat.parse(csvRecord.get(0));
					 String convertedDate = dateFormatNeeded.format(dategot);
					list.add(new TrafficViolation(csvRecord.get(8), csvRecord
							.get(2), csvRecord.get(16), csvRecord.get(33),
							csvRecord.get(26), csvRecord.get(9), csvRecord
									.get(25), csvRecord.get(23), csvRecord
									.get(13), csvRecord.get(15), csvRecord
									.get(27), convertedDate, csvRecord
									.get(4), csvRecord.get(32), csvRecord
									.get(30), csvRecord.get(31), csvRecord
									.get(12), csvRecord.get(29), csvRecord
									.get(14), csvRecord.get(5), csvRecord
									.get(21), csvRecord.get(22), csvRecord
									.get(10), csvRecord.get(11), csvRecord
									.get(28), csvRecord.get(18), csvRecord
									.get(3), csvRecord.get(1), csvRecord
									.get(19), csvRecord.get(24), csvRecord
									.get(17), csvRecord.get(20)));
					if (list.size() == 100000) {
						TrafficViolationCollector c = new TrafficViolationCollector();
						c.mungee(list);
						c.save(list);
						c.saveElasticsearch(list);
						System.out.println("100000 records are inserted in mongo and elasticsearch");
						count = 0;
						list = new ArrayList<TrafficViolation>();
					}
					count++;
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}

			}
			TrafficViolationCollector c = new TrafficViolationCollector();
			c.mungee(list);
			c.save(list);
			c.saveElasticsearch(list);
			list = new ArrayList<TrafficViolation>();
			System.out.println("Total " + count + " inserted in mongo and elasticsearch");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public boolean hasNext() {
		return count > 1;
	}

	@Override
	public Collection<TrafficViolation> next() {
		return list;
	}
}
