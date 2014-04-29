package com.example.carte.basededonne;

import com.google.android.gms.drive.internal.m;
import com.google.android.gms.location.d;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

//public class BdOpenHelper extends SQLiteOpenHelper {
/*	
	//COLUMNS NAME
	public static final String  C_ID="_id";
	
	public static final String  C_DEVID="devid";
	public static final String  C_ADR="address";
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
	
	private static final String T_SCAN_CREATE="create table "+T_SCAN
												+" ("+C_SCANID+" integer primary key AUTOINCREMENT, "// ATTENTION AUTOINSCREMENT
												+C_TIME+" TEXT not null,"
												+C_GEOID+" INTEGER not null);";
	
	private static final String T_GEOLOC_CREATE="create table "+T_GEOLOC
																+" ("+C_GEOID+" integer primary key AUTOINCREMENT,"// ATTENTION AUTOINSCREMENT
																+C_LON+" REAL not null,"
																+C_LAT+" REAL not null), "
																+C_TIME+ "TEXT not null;";
	

	private static final String T_DEVICE_CREATE="create table"+T_DEVICE
									+" ("+C_DEVID+" integer primary key AUTOINCREMENT," 
									+C_ADR+" text not null,"
									+C_NAME+" text," 
									+C_TYPE+" INTEGER not null);";

	private static final String T_LOG_CREATE="create table "+T_LOG
									+" ("+C_ID+" integer primary key AUTOINCREMENT,"
									+C_DEVID+" integer not null,"
									+C_SCANID+" integer not null,"
									+C_GEOID+" integer not null)" 
									+C_TIME+" TEXT not null);"; 
		
	
	
	public BdOpenHelper(Context context, String name, CursorFactory factory, int version) {
    	super(context, name, factory, version);
  	}
	
	public BdOpenHelper(Context context) {
		super(context,DB_NAME, null, DB_VER);
		Log.d(getClass().getName(), "constructor");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(T_DEVICE_CREATE);
		db.execSQL(T_SCAN_CREATE);
		db.execSQL(T_GEOLOC_CREATE);
		db.execSQL(T_LOG_CREATE);
		Log.d(getClass().getName(),"create");//sert a quoi?
	}
	public static final String T_SCAN_DROP = "DROP TABLE IF EXISTS " + T_SCAN + ";";
	public static final String T_DEVICE_DROP = "DROP TABLE IF EXISTS " + T_DEVICE+ ";";
	public static final String T_GEOLOC_DROP = "DROP TABLE IF EXISTS " + T_GEOLOC + ";";
	public static final String T_LOG_DROP = "DROP TABLE IF EXISTS " + T_LOG + ";";
	


	private SQLiteDatabase bdd;
 
	private MaBaseSQLite maBaseSQLite;
 
	public LivresBDD(Context context){
		//On créer la BDD et sa table
		maBaseSQLite = new MaBaseSQLite(context, NOM_BDD, null, VERSION_BDD);
	}
 
	public void open(){
		//on ouvre la BDD en écriture
		bdd = maBaseSQLite.getWritableDatabase();
	}
 
	public void close(){
		//on ferme l'accès à la BDD
		bdd.close();
	}
 
	public SQLiteDatabase getBDD(){
		return bdd;
	}
	//---------- MODIFICATION
	public long ModifScan(Scan s){
		ContentValues value = new ContentValues();
		value.put(C_TIME, m.getTime());
		mDb.update(T_SCAN, value, C_SCANID  + " = ?", new String[] {String.valueOf(s.getId())});
	}
 /// -------------- insertion DANS SCAN 
 
	public long insertScan(Scan s){
		//Création d'un ContentValues (fonctionne comme une HashMap)
		
		ContentValues values = new ContentValues();
		//on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
		//values.put(C_SCANID, s.getId());=> pas besoin car autoincrement
		values.put(C_TIME, s.getTime());
	//	values.put(C_GEOID s.getid());
		//on insère l'objet dans la BDD via le ContentValues
		return bdd.insert(T_SCAN, null, values);
	}
 /// -------------- insertion DANS DEVICE
	public long insertDevice(Device d){
		//Création d'un ContentValues (fonctionne comme une HashMap)
		ContentValues values = new ContentValues();
		//on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
		
		//values.put(C_DEVID, d.getID());
		values.put(C_ADR, d.getAdr());
		values.put(C_NAME, d.getTime());
		values.put(C_TYPE, d.getType());
		
		//on insère l'objet dans la BDD via le ContentValues
		return bdd.insert(T_SCAN_COLS, null, values);
	}
 /// -------------- insertion DANS geolocalisation
 
 	public long insertGeoloc(Geoloc g){
		//Création d'un ContentValues (fonctionne comme une HashMap)
		ContentValues values = new ContentValues();
		//on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)

		values.put(C_LON, g.getLon());
		values.put(C_LAT, g.getLat());
		values.put(C_TIME, g.getTime());
		
		//on insère l'objet dans la BDD via le ContentValues
		return bdd.insert(T_SCAN_COLS, null, values);
	}
 /// -------------- insertion DANS LOG
 	public long insertLog(Pricipale l){
		//Création d'un ContentValues (fonctionne comme une HashMap)
		ContentValues values = new ContentValues();
		//on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)

		values.put(C_DEVID, l.getId());
		values.put(C_GEOID, l.getId());
		values.put(C_SCANID, l.getId());
		
		//on insère l'objet dans la BDD via le ContentValues
		return bdd.insert(T_SCAN_COLS, null, values);
	}
	
	////////////////////////////////////////////////////////////////////

	@Override

	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		Log.d(getClass().getName(),"updated");
		resetAll(db);
		 
		db.execSQL(T_DEVICE_CREATE);
		onCreate(db);
		db.execSQL(T_SCAN_CREATE);
		onCreate(db);
		db.execSQL(T_GEOLOC_CREATE);
		onCreate(db);
		db.execSQL(T_LOG_CREATE);
		onCreate(db);
		
	}
	
	public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion){
		// TODO Auto-generated method stub
		Log.d(getClass().getName(),"downgradeds");
		resetAll(db);
	}
	
	public void resetAll(SQLiteDatabase db){
		db.execSQL("DROP TABLE IF EXISTS "+T_GEOLOC);
		db.execSQL("DROP TABLE IF EXISTS "+T_DEVICE);
		db.execSQL("DROP TABLE IF EXISTS "+T_SCAN);
		db.execSQL("DROP TABLE IF EXISTS "+T_LOG);
		Log.d(getClass().getName(),"reset");
		onCreate(db);		
	}
*/
//}
