package com.example.carte.basededonne;
 

public class Device {
	private int devid;
	private String adresse;
	private String name;
	private String type;

	public Device(int devid ,String name ,String adresse, String type) {
		 super();
		 this.devid = devid;
		 this.name = name;
		 this.adresse = adresse;
		 this.adresse = adresse;
	}
	// ------------- GETTER --------------//
	public int getDevId() {
		return devid;
	}
	public String getName() {
		return name;
	}	
	public String getAdresse() {
			return adresse;
	}
	public String getType() {
		return type;
	}


	// ------------- SETTER --------------//
	public void setDevId( int devid ) {
		this.devid = devid;
	}
		
	public void setName(String name) {
		this.name = name;
	}
	
		
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
		
	public void setType(String type) {
		this.type = type;
	}
	
}

