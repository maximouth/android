package com.example.carte.basededonne;
 

public class Geoloc {
	private int geoid;
	private double lat;
	private double lon;

	public Geoloc(int geoid ,double lat ,double lon) {
		 super();
		 this.geoid = geoid;
		 this.lat = lat;
		 this.lon = lon;
	}

	// ------------- GETTER --------------//
	public int getGeoId() {
		return geoid;
	}
	public double getLat() {
		return lat;
	}	
	public double getLon(){
			return lon;
	}



	// ------------- SETTER --------------//
	public void setGeoId(int geoid ){
		this.geoid = geoid;
	}
		
	public void setLat(double lat){
		this.lat = lat;
	}
	
		
	public void setLon(double lon){
		this.lon = lon;
	}
		
	
}

