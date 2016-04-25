package edu.csula.datascience.acquisition;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TrafficViolation {

	private String accident;
	private String agency;
	private String alcohol;
	private String arrest_type;
	private String article;
	private String belts;
	private String charge;
	private String color;
	private String commercial_license;
	private String commercial_vehicle;
	private String contributed_to_accident;
	private String date_of_stop;
	private String description;
	private String dl_state;
	private String driver_city;
	private String driver_state;
	private String fatal;
	private String gender;
	private String hazmat;
	private String location;
	private String make;
	private String model;
	private String personal_injury;
	private String property_damage;
	private String race;
	private String state;
	private String subagency;
	private String time_of_stop;
	private String vehicle_type;
	private String violation_type;
	private String work_zone;
	private String year;

	public TrafficViolation() {

	}

	public TrafficViolation(String accident, String agency, String alcohol,
			String arrest_type, String article, String belts, String charge,
			String color, String commercial_license, String commercial_vehicle,
			String contributed_to_accident, String date_of_stop,
			String description, String dl_state, String driver_city,
			String driver_state, String fatal, String gender, String hazmat,
			String location, String make, String model, String personal_injury,
			String property_damage, String race, String state,
			String subagency, String time_of_stop, String vehicle_type,
			String violation_type, String work_zone, String year) {
		super();
		this.accident = accident;
		this.agency = agency;
		this.alcohol = alcohol;
		this.arrest_type = arrest_type;
		this.article = article;
		this.belts = belts;
		this.charge = charge;
		this.color = color;
		this.commercial_license = commercial_license;
		this.commercial_vehicle = commercial_vehicle;
		this.contributed_to_accident = contributed_to_accident;
		this.date_of_stop = date_of_stop;
		this.description = description;
		this.dl_state = dl_state;
		this.driver_city = driver_city;
		this.driver_state = driver_state;
		this.fatal = fatal;
		this.gender = gender;
		this.hazmat = hazmat;
		this.location = location;
		this.make = make;
		this.model = model;
		this.personal_injury = personal_injury;
		this.property_damage = property_damage;
		this.race = race;
		this.state = state;
		this.subagency = subagency;
		this.time_of_stop = time_of_stop;
		this.vehicle_type = vehicle_type;
		this.violation_type = violation_type;
		this.work_zone = work_zone;
		this.year = year;
	}

	public String getAccident() {
		return accident;
	}

	public void setAccident(String accident) {
		this.accident = accident;
	}

	public String getAgency() {
		return agency;
	}

	public void setAgency(String agency) {
		this.agency = agency;
	}

	public String getAlcohol() {
		return alcohol;
	}

	public void setAlcohol(String alcohol) {
		this.alcohol = alcohol;
	}

	public String getArrest_type() {
		return arrest_type;
	}

	public void setArrest_type(String arrest_type) {
		this.arrest_type = arrest_type;
	}

	public String getArticle() {
		return article;
	}

	public void setArticle(String article) {
		this.article = article;
	}

	public String getBelts() {
		return belts;
	}

	public void setBelts(String belts) {
		this.belts = belts;
	}

	public String getCharge() {
		return charge;
	}

	public void setCharge(String charge) {
		this.charge = charge;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getCommercial_license() {
		return commercial_license;
	}

	public void setCommercial_license(String commercial_license) {
		this.commercial_license = commercial_license;
	}

	public String getCommercial_vehicle() {
		return commercial_vehicle;
	}

	public void setCommercial_vehicle(String commercial_vehicle) {
		this.commercial_vehicle = commercial_vehicle;
	}

	public String getContributed_to_accident() {
		return contributed_to_accident;
	}

	public void setContributed_to_accident(String contributed_to_accident) {
		this.contributed_to_accident = contributed_to_accident;
	}

	public String getDate_of_stop() {
		return date_of_stop;
	}

	public void setDate_of_stop(String date_of_stop) {
		this.date_of_stop = date_of_stop;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDl_state() {
		return dl_state;
	}

	public void setDl_state(String dl_state) {
		this.dl_state = dl_state;
	}

	public String getDriver_city() {
		return driver_city;
	}

	public void setDriver_city(String driver_city) {
		this.driver_city = driver_city;
	}

	public String getDriver_state() {
		return driver_state;
	}

	public void setDriver_state(String driver_state) {
		this.driver_state = driver_state;
	}

	public String getFatal() {
		return fatal;
	}

	public void setFatal(String fatal) {
		this.fatal = fatal;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getHazmat() {
		return hazmat;
	}

	public void setHazmat(String hazmat) {
		this.hazmat = hazmat;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getPersonal_injury() {
		return personal_injury;
	}

	public void setPersonal_injury(String personal_injury) {
		this.personal_injury = personal_injury;
	}

	public String getProperty_damage() {
		return property_damage;
	}

	public void setProperty_damage(String property_damage) {
		this.property_damage = property_damage;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getSubagency() {
		return subagency;
	}

	public void setSubagency(String subagency) {
		this.subagency = subagency;
	}

	public String getTime_of_stop() {
		return time_of_stop;
	}

	public void setTime_of_stop(String time_of_stop) {
		this.time_of_stop = time_of_stop;
	}

	public String getVehicle_type() {
		return vehicle_type;
	}

	public void setVehicle_type(String vehicle_type) {
		this.vehicle_type = vehicle_type;
	}

	public String getViolation_type() {
		return violation_type;
	}

	public void setViolation_type(String violation_type) {
		this.violation_type = violation_type;
	}

	public String getWork_zone() {
		return work_zone;
	}

	public void setWork_zone(String work_zone) {
		this.work_zone = work_zone;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

}
