package com.example.carte.basededonne;


public class Scan {
	private int scanid;
	private String time;

	public Scan(int scanid, String time) {
		 super();
		 this.scanid = scanid;
		 this.time = time;
		}

		public int getScanId() {
			return scanid;
		}
		
		public void setId( int scanid ) {
			this.scanid = scanid;
		}
		
		public String getTime() {
			return time;
		}
		
		public void setTime(String time) {
			this.time = time;
		}
		
	
}

