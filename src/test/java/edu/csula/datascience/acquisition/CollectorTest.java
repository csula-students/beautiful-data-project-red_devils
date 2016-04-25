package edu.csula.datascience.acquisition;

import com.google.common.collect.Lists;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * A test case to show how to use Collector and Source
 */
public class CollectorTest {
    private Collector<SimpleModel, MockData> collector;
    private Source<MockData> source;

    @Before
    public void setup() {
        collector = new MockCollector();
        source = new MockSource();
    }

    @Test
    public void mungee() throws Exception {
    	List<SimpleModel> list = (List<SimpleModel>) collector.mungee(source.next());     
        Assert.assertEquals(list.size(), 2);
    }
    @Test
    public void checkNull()
    {
    	List<SimpleModel> list = (List<SimpleModel>) collector.mungee(source.next()); 
    	Assert.assertNotNull(list);
    }
    @Test
    public void testNullCSV()
    {
    	MockSource src=new MockSource();
    	List<SimpleModel> list = (List<SimpleModel>) collector.mungee(src.readCSV()); 
    	Assert.assertNotNull(list);
    }
    @Test
    public void testDataCSV()
    {
    	MockSource src=new MockSource();
    	List<SimpleModel> list = (List<SimpleModel>) collector.mungee(src.readCSV()); 
    	List<SimpleModel> expectedList=Lists.newArrayList(new SimpleModel("NO", "mail", "No", "A-Marked",
				"Tranpotation", "NO", "21-707(a)", "blue", "NO", "NO", "NO",
				"09/30/2014", "description", "CA", "California", "CA", "Yes",
				"M", "Yes", "location", "Ford", "BMW", "Yes", "NO", "Asian",
				"CA", "4th distric", "23:51:00", "NO", "Automobile", "NO",
				"2013"), 
				new SimpleModel("NO", "mail", "No", "A-Marked",
				"Tranpotation", "NO", "21-707(a)", "blue", "NO", "NO", "NO",
				"03/31/2015", "description", "CA", "California", "CA", "Yes",
				"M", "Yes", "location", "Ford", "BMW", "Yes", "NO", "Asian",
				"CA", "4th distric", "23:59:00", "NO", "Automobile", "NO",
				"2013"));
    	Assert.assertEquals(list.size(), 3);
    	
    	for(int i=1;i<3;i++)
    	{
    		 Assert.assertEquals(list.get(i).getDate_of_stop(), expectedList.get(i-1).getDate_of_stop());
             Assert.assertEquals(list.get(i).getTime_of_stop(), expectedList.get(i-1).getTime_of_stop());
    	}
    }
}