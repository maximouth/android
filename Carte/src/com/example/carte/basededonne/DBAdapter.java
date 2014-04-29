package com.example.carte.basededonne;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

// class d'interaction avec la BD
public class DBAdapter {
	 static DataBaseHelper DBHelper;
	 Context context;
	 SQLiteDatabase db;
	
	 //COLUMNS NAME
	public static final String  C_ID="_id";
		
	public static final String  C_DEVID="devid";
	public static final String  C_ADR="adresse";
	public static final String  C_NAME="name";
	public static final String  C_TYPE="type";
	
	public static final String  C_GEOID="geoid";
	public static final String  C_LAT="lat";
	public static final String  C_LON="lon";
		
	public static final String  C_SCANID="scanid";
	public static final String  C_TIME="time";
		
	//DB NAME
	public static final String DB_NAME="scanwifi.db";
	public static final int DB_VER=1;
		
		
	// Tables Name
	public static final String  T_DEVICE="devices";
	public static final String  T_SCAN="scans";
	public static final String  T_GEOLOC="geoloc";
	public static final String  T_LOG="logs";
		
	public static final String[] T_DEVICE_COLS={C_DEVID, C_ADR, C_NAME, C_TYPE};
	public static final String[] T_SCAN_COLS={C_SCANID, C_TIME};
	public static final String[] T_GEOLOC_COLS={C_GEOID, C_LAT, C_LON, C_TIME};
	public static final String[] T_LOG_COLS={C_ID, C_DEVID, C_SCANID, C_GEOID, C_TIME};
		
		
	 public DBAdapter(Context context,String name) {
				this.context= context;
				if (DBHelper == null) 
				DBHelper= new DataBaseHelper(context,name);
				
	}
		
	 public class DataBaseHelper extends SQLiteOpenHelper{

		 public DataBaseHelper(Context context, String name) {
				super(context, name, null, 1);
				this.context= context;
				
		 }	  
		 Context context;
		 

		@Override
		public void onCreate(SQLiteDatabase db) {
			createAll(db);	
		}
		
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			resetAll(db);
			onCreate(db);
		}
		public void resetAll(SQLiteDatabase db){
			db.execSQL("DROP TABLE IF EXISTS T_GEOLOC");
			db.execSQL("DROP TABLE IF EXISTS T_DEVICE");
			db.execSQL("DROP TABLE IF EXISTS T_SCAN");
			db.execSQL("DROP TABLE IF EXISTS T_LOG");
			Log.d(getClass().getName(),"reset");
			onCreate(db);		
		}
		
		public void createAll(SQLiteDatabase db){
			db.execSQL(T_SCAN_CREATE);
			db.execSQL(T_GEOLOC_CREATE);
			db.execSQL(T_DEVICE_CREATE);
			db.execSQL(T_LOG_CREATE);
		}
	 }
		//---------------------------------------------------------------//	 
		//------------------ CREATION DES BASE DE DONNEE ----------------//
		//---------------------------------------------------------------//	 
		     private static final String T_SCAN_CREATE="create table T_SCAN (C_SCANID integer primary key AUTOINCREMENT, "
						+"C_TIME TEXT not null,"
						+"C_GEOID INTEGER not null);";
		
		     private static final String T_GEOLOC_CREATE="create table T_GEOLOC (C_GEOID integer primary key AUTOINCREMENT,"
										+"C_LON REAL not null,"
										+"C_LAT REAL not null, "
										+"C_TIME TEXT not null);";
		
		
			private static final String T_DEVICE_CREATE="create table T_DEVICE (C_DEVID integer primary key AUTOINCREMENT," 
				+"C_ADR text not null,"
				+"C_NAME text," 
				+"C_TYPE INTEGER not null);";
		
			private static final String T_LOG_CREATE="create table T_LOG(C_ID integer primary key AUTOINCREMENT,"
				+"C_DEVID integer not null,"
				+"C_SCANID integer not null,"
				+"C_GEOID integer not null,"
				+"C_TIME TEXT not null);";

			
			public DBAdapter open(){
				db=DBHelper.getWritableDatabase();
				return this;
			}
			
			public void close(){
				db.close();
			}
			
			// efface tt les donnees de la bd
			public void Truncate(){
				db.execSQL("DELETE FROM T_SCAN_CREATE");
				db.execSQL("DELETE FROM T_GEOLOC_CREATE");
				db.execSQL("DELETE FROM T_DEVICE_CREATE");
				db.execSQL("DELETE FROM T_LOG_CREATE");
			}
			
			
			//-----------------------------------------------//	 
			//------------------ INSERTION ------------------//
			//-----------------------------------------------//	 
/*	    
	
	     private static final String T_GEOLOC_CREATE="create table T_GEOLOC (C_GEOID integer primary key AUTOINCREMENT,"
									+"C_LON REAL not null,"
									+"C_LAT REAL not null, "
									+"C_TIME TEXT not null);";
	
	*/
			public long insererSCAN (String time){
				ContentValues values =new ContentValues();// pack qui va contenir toutes nos variables
				values.put("C_TIME",time);
				return db.insert(T_SCAN_CREATE, null, values);
			}
			public long insererGEOLOC (double longitude, double latitude, String date){
				ContentValues values =new ContentValues();// pack qui va contenir toutes nos variables
				values.put("C_LON",longitude);
				values.put("C_LAT",latitude);
				values.put("date",date);
				return db.insert(T_GEOLOC_CREATE, null, values);
			}

			public long insererDEVICE (String adresse, String name,String type){
				ContentValues values =new ContentValues();// bagage qui va contenir toutes nos donnees
				values.put("C_ADR",adresse);
				values.put("C_NAME",name);
				values.put("C_TYPE",type);
				return db.insert(T_DEVICE_CREATE, null, values);
			}

			public long insererLOG (int devID, int scanID, int geoID, String time){
				ContentValues values =new ContentValues(); // pack qui va contenir toutes nos variables
				values.put("C_DEVID",devID);
				values.put("C_SCANID",scanID);
				values.put("C_GEOID",geoID);
				values.put("C_TIME",time);
				return db.insert(T_LOG_CREATE, null, values);
			}
			
			//-----------------------------------------------//	 
			//------------------ SUPPRESSION ----------------//
			//-----------------------------------------------//	 
			
			public boolean supprimerSCAN(){
				return db.delete("T_SCAN_CREATE", "_id="+C_SCANID, null)>0;
			}
			public boolean supprimerGEOLOC(){
				return db.delete("T_GEOLOC_CREATE", "_id="+C_GEOID, null)>0;
			}
			public boolean supprimerDEVICE(){
				return db.delete("T_DEVICE_CREATE", "_id="+C_DEVID, null)>0;
			}
			public boolean supprimerLOG(){
				return db.delete("T_LOG_CREATE", "_id="+C_ID, null)>0;
			}

			
			public Cursor recupererLaListeDesScan(){
				return db.query("T_SCAN_CREATE",T_SCAN_COLS, null, null, null, null, null);	
			}
			
	 	
}

