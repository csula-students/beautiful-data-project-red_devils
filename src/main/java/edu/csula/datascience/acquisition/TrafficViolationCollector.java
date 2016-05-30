package edu.csula.datascience.acquisition;

import static org.elasticsearch.node.NodeBuilder.nodeBuilder;

import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;
import org.elasticsearch.action.bulk.BackoffPolicy;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.node.Node;

import java.net.URISyntaxException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


public class TrafficViolationCollector implements Collector<TrafficViolation, TrafficViolation> {
    MongoClient mongoClient;
    MongoDatabase database;
    MongoCollection<Document> collection;
    final String indexName = "big-data";
    final String typeName = "trafficViolations";
    public TrafficViolationCollector() {
    	
        // establish database connection to MongoDB
    	
        mongoClient = new MongoClient();
        
        // select `big-data` as testing database
        database = mongoClient.getDatabase("big-data");

        // select collection by name `trafficViolations`
        collection = database.getCollection("trafficViolations");
    }
    @Override
    public Collection<TrafficViolation> mungee(Collection<TrafficViolation> src) {
    	return src
        .stream()
        .filter(data -> data.getDate_of_stop() != null)
        .filter(data -> data.getTime_of_stop() != null)
        .filter(data -> data.getDescription() != null)
        .filter(data -> data.getAccident() != null)
        .filter(data -> data.getAlcohol() != null)
        .filter(data -> data.getAgency() != null)
        .filter(data -> data.getBelts() != null)
        .filter(data -> data.getArrest_type() != null)
        .filter(data -> data.getCharge() != null)
        .filter(data -> data.getContributed_to_accident() != null)
        .filter(data -> data.getFatal() != null)
        .filter(data -> data.getPersonal_injury() != null)
        .filter(data -> data.getProperty_damage() != null)
        .collect(Collectors.toList());
    }
    

    @Override
    public void save(Collection<TrafficViolation> data) {    	
    	  List<Document> documents = data.stream().map(item -> new Document()
    	  .append("accident", item.getAccident())
          .append("agency", item.getAgency())
           .append("alcohol", item.getAlcohol())
           .append("arrest_type", item.getArrest_type())
           .append("article", item.getArticle())
           .append("belts", item.getBelts())
           .append("charge", item.getCharge())
           .append("color", item.getColor())
           .append("commercial_license", item.getCommercial_license())
           .append("commercial_vehicle", item.getCommercial_vehicle())
           .append("contributed_to_accident", item.getContributed_to_accident())
           .append("date_of_stop", item.getDate_of_stop())
           .append("description", item.getDescription())
           .append("dl_state", item.getDl_state())
           .append("driver_city", item.getDriver_city())
           .append("driver_state", item.getDriver_state())
           .append("fatal", item.getFatal())
           .append("gender", item.getGender())
           .append("hazmat", item.getHazmat())
           .append("location", item.getLocation())
           .append("make", item.getMake())
           .append("model", item.getModel())
           .append("personal_injury", item.getPersonal_injury())
           .append("property_damage", item.getProperty_damage())
           .append("race", item.getRace())
           .append("state", item.getState())
           .append("subagency", item.getSubagency())
           .append("time_of_stop", item.getTime_of_stop())
           .append("vehicle_type", item.getVehicle_type())
           .append("violation_type", item.getViolation_type())
            .append("work_zone", item.getWork_zone())
            .append("year",item.getYear()))
  	            .collect(Collectors.toList());
        collection.insertMany(documents);
        mongoClient.close();
    }
    
    public void saveElasticsearch(Collection<TrafficViolation> data) throws URISyntaxException
    {
    	//mapping
    	/*PUT big-data
    	{
    	  "mappings": {
    	    "trafficViolations":{
    	      "properties": {
    	        "accident":{
    	          "type": "boolean"
    	        },
    	        "agency":{
    	          "type": "string"
    	          , "index": "not_analyzed"
    	        },
    	        "alcohol":{
    	          "type": "boolean"
    	      },
    	      "arrest_type":{
    	        "type": "string"
    	        , "index": "not_analyzed"
    	      },
    	      "article":{
    	        "type": "string"
    	        , "index": "not_analyzed"
    	      },
    	      "belts":{
    	        "type": "boolean"
    	      },
    	      "charge":{
    	        "type": "string"
    	        , "index": "not_analyzed"
    	      },
    	      "color":{
    	        "type": "string"
    	        , "index": "not_analyzed"
    	      },
    	      "commercial_license":{
    	        "type": "string"
    	        , "index": "not_analyzed"
    	      },
    	      "commercial_vehicle":{
    	        "type": "string"
    	        , "index": "not_analyzed"
    	      },
    	      "contributed_to_accident":{
    	        "type": "string"
    	        , "index": "not_analyzed"
    	      },
    	      "date_of_stop":{
    	        "type": "date"
    	      },
    	       "description":{
    	        "type": "string"
    	        , "index": "not_analyzed"
    	      },
    	       "dl_state":{
    	        "type": "string"
    	        , "index": "not_analyzed"
    	      },
    	      "driver_city":{
    	        "type": "string"
    	        , "index": "not_analyzed"
    	      },
    	      "fatal":{
    	        "type": "boolean"
    	      },
    	      "gender":{
    	        "type": "string"
    	        , "index": "not_analyzed"
    	      },
    	      "hazmat":{
    	        "type": "boolean"
    	      },
    	      "location":{
    	        "type": "string"
    	        , "index": "not_analyzed"
    	      },
    	      "make":{
    	        "type": "string"
    	        , "index": "not_analyzed"
    	      },
    	      "model":{
    	        "type": "string"
    	        , "index": "not_analyzed"
    	      },
    	      "personal_injury":{
    	        "type": "boolean"
    	      },
    	       "property_damage":{
    	        "type": "boolean"
    	      },
    	      "race":{
    	        "type": "string"
    	        , "index": "not_analyzed"
    	      },
    	      "state":{
    	        "type": "string"
    	        , "index": "not_analyzed"
    	      },
    	      "subagency":{
    	        "type": "string"
    	        , "index": "not_analyzed"
    	      },
    	      "time_of_stop":{
    	        "type": "string"
    	        , "index": "not_analyzed"
    	      },
    	     "vehicle_type":{
    	        "type": "string"
    	        , "index": "not_analyzed"
    	      },
    	      "work_zone":{
    	        "type": "string"
    	        , "index": "not_analyzed"
    	      },
    	      "year":{
    	        "type": "integer"
    	      }
    	      }
    	  }
    	}
    	}*/
    	System.out.println("inserting in to elasticsearch");
        Node node = nodeBuilder().settings(Settings.builder()
            	.put("cluster.name","DipeshPatel")
                .put("path.home", "elasticsearch-data")).node();
            Client client = node.client();

            // create bulk processor
            BulkProcessor bulkProcessor = BulkProcessor.builder(
                client,
                new BulkProcessor.Listener() {
                    @Override
                    public void beforeBulk(long executionId,
                                           BulkRequest request) {
                    }

                    @Override
                    public void afterBulk(long executionId,
                                          BulkRequest request,
                                          BulkResponse response) {
                    }

                    @Override
                    public void afterBulk(long executionId,
                                          BulkRequest request,
                                          Throwable failure) {
                        System.out.println("Facing error while importing data to elastic search");
                        failure.printStackTrace();
                    }
                })
                .setBulkActions(10000)
                .setBulkSize(new ByteSizeValue(1, ByteSizeUnit.GB))
                .setFlushInterval(TimeValue.timeValueSeconds(5))
                .setConcurrentRequests(1)
                .setBackoffPolicy(
                    BackoffPolicy.exponentialBackoff(TimeValue.timeValueMillis(100), 3))
                .build();
            Gson gson=new Gson();
            for (TrafficViolation trafficViolation : data) {
            	bulkProcessor.add(new IndexRequest(indexName, typeName)
                .source(gson.toJson(trafficViolation))
            );
			}
            System.out.println("finish inserting");
    }
}
