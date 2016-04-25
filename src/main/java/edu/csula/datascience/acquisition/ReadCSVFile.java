package edu.csula.datascience.acquisition;

import java.io.File;
import java.io.FileWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.json.JSONArray;
import org.json.JSONObject;

//Read CSV file and Write in JSON file 
public class ReadCSVFile {

	public static void main(String args[]) {
		String fileName="/home/dipesh/TrafficViolation/Traffic_Violations.csv";
		File file=new File(fileName);
		readData(file);
	}

	public static void readData(File descriptionCsvFile) {
		CSVParser parser;
		List<TrafficViolation> list=new ArrayList<TrafficViolation>();
		try {
			parser = CSVParser.parse(descriptionCsvFile,
					Charset.defaultCharset(), CSVFormat.DEFAULT);
			for (CSVRecord csvRecord : parser) {
				try {
					list.add(new TrafficViolation(csvRecord.get(8), csvRecord.get(2), csvRecord.get(16), csvRecord.get(33), 
							csvRecord.get(26), csvRecord.get(9), csvRecord.get(25), csvRecord.get(23), csvRecord.get(13), 
							csvRecord.get(15), csvRecord.get(27), csvRecord.get(0), csvRecord.get(4), csvRecord.get(32),
							csvRecord.get(30), csvRecord.get(31), csvRecord.get(12),csvRecord.get(29), csvRecord.get(14), csvRecord.get(5), 
							csvRecord.get(21), csvRecord.get(22), csvRecord.get(10), csvRecord.get(11), csvRecord.get(28), csvRecord.get(18), 
							csvRecord.get(3), csvRecord.get(1), csvRecord.get(19), csvRecord.get(24), csvRecord.get(17), csvRecord.get(20)));
					if(list.size()==5)
					{
						saveData(list);
						list.clear();
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}				
			}	
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	public static void saveData(List<TrafficViolation> list)
	{
		JSONArray jsonarray=new JSONArray();
		JSONObject obj=new JSONObject();
		obj.put("TrafficViolation", list);
		jsonarray.put(obj);
		try
		{
			FileWriter file=new FileWriter("/home/dipesh/Desktop	/Traffic_Violation.json");
			file.append(obj.toString());
			file.flush();
			file.close();
			System.out.println("Complete 5 rows");
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
