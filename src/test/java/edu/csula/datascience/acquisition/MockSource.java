package edu.csula.datascience.acquisition;

import com.google.common.collect.Lists;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 * A mock source to provide data
 */
public class MockSource implements Source<MockData> {
	int index = 0;

	@Override
	public boolean hasNext() {
		return index < 1;
	}

	@Override
	public Collection<MockData> next() {
		return Lists.newArrayList(new MockData("NO", "mail", "No", "A-Marked",
				"Tranpotation", "NO", "21-707(a)", "blue", "NO", "NO", "NO",
				"2013/01/01", "description", "CA", "California", "CA", "Yes",
				"M", "Yes", "location", "Ford", "BMW", "Yes", "NO", "Asian",
				"CA", "4th distric", "20:04:00", "NO", "Automobile", "NO",
				"2013"), 
				new MockData("NO", "mail", "No", "A-Marked",
				"Tranpotation", "NO", "21-707(a)", "blue", "NO", "NO", "NO",
				"2013/01/01", "description", "CA", "California", "CA", "Yes",
				"M", "Yes", "location", "Ford", "BMW", "Yes", "NO", "Asian",
				"CA", "4th distric", "20:04:00", "NO", "Automobile", "NO",
				"2013"), 
				new MockData(null, null, "No", null, "Tranpotation",
				"NO", "21-707(a)", "blue", "NO", "NO", "NO", "2013/01/01",
				"description", "CA", "California", "CA", "Yes", "M", "Yes",
				"location", "Ford", "BMW", "Yes", "NO", "Asian", "CA",
				"4th distric", "20:04:00", "NO", "Automobile", "NO", "2013"));
	}

	public Collection<MockData> readCSV()
    {
    	List<MockData> list=new ArrayList<MockData>();
    	String fileName="";
    //	fileName="D:/cs594 traffic violation/Traffic_Violations.csv";
		File file=new File(fileName);
    	CSVParser parser;
		try {
			parser = CSVParser.parse(file,
					Charset.defaultCharset(), CSVFormat.DEFAULT);
			for (CSVRecord csvRecord : parser) 
			{
				try 
				{
					list.add(new MockData(csvRecord.get(8), csvRecord.get(2), csvRecord.get(16), csvRecord.get(33), 
							csvRecord.get(26), csvRecord.get(9), csvRecord.get(25), csvRecord.get(23), csvRecord.get(13), 
							csvRecord.get(15), csvRecord.get(27), csvRecord.get(0), csvRecord.get(4), csvRecord.get(32),
							csvRecord.get(30), csvRecord.get(31), csvRecord.get(12),csvRecord.get(29), csvRecord.get(14), csvRecord.get(5), 
							csvRecord.get(21), csvRecord.get(22), csvRecord.get(10), csvRecord.get(11), csvRecord.get(28), csvRecord.get(18), 
							csvRecord.get(3), csvRecord.get(1), csvRecord.get(19), csvRecord.get(24), csvRecord.get(17), csvRecord.get(20)));
					if(list.size()==3)
					{
						return list;
					}
				} 
				catch (Exception e) 
				{
					System.out.println(e.getMessage());
				}
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return list;
    }
}