package com.example.carte.basededonne;
	

public class Log {
	private int id;
	private int devid;
	private int scanid;
	private int geoid;


	public Log(int id ,int devid ,int scanid, int geoid) {
		 super();
		 this.id = id;
		 this.devid = devid;
		 this.scanid = scanid;
		 this.geoid = geoid;
	}

	// ------------- GETTER --------------//
	public int getId() {
		return id;
	}
	public int getScanId() {
		return scanid;
	}
	public int getGeoId() {
		return geoid;
	}
	public int getDevId() {
		return devid;
	}


	// ------------- SETTER --------------//

		
	public void setDevID(int devid){
		this.devid = devid;
	}
	
		
	public void setScanID(int scanid){
		this.scanid = scanid;
	}
	
	public void setGeoId(int geoid){
		this.geoid = geoid;
	}

	
		
	
}

