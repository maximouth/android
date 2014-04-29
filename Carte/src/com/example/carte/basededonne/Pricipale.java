package com.example.carte.basededonne;

public class Pricipale {
	private int Id;
	private String Time;

	public Pricipale(int Id, String Time) {
	 super();
	 this.Id = Id;
	 this.Time = Time;
	}

	public int getId() {
		return Id;
	}
	
	public void setId( int Id ) {
		this.Id = Id;
	}
	
	public String getTime() {
		return Time;
	}
	
	public void setTime(String Time) {
		this.Time = Time;
	}
	
}
