package edu.csula.datascience.acquisition;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


public class TrafficViolationCollector implements Collector<TrafficViolation, TrafficViolation> {
    MongoClient mongoClient;
    MongoDatabase database;
    MongoCollection<Document> collection;
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
}
