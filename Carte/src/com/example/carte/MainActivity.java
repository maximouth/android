package com.example.carte;



import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.util.Log;

import com.example.carte.basededonne.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;







import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.support.v4.app.*;

import com.example.carte.ItemizedOverlay.ItimizedOverlay;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toast;







import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;



/*
@SuppressLint("SimpleDateFormat")
public class MainActivity extends MapActivity implements LocationListener {

	
	//optenir la date
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	String dateandTime = sdf.format(new Date());
	
	public Calendar c = Calendar.getInstance();
	private MapView mapView;
	private MapController mc;
	
	
	private LocationManager lm;
	
	private double latitude;
	private double longitude;
	private double latA;
	private double longA;
	private double altitude;
	private double accuracy;
	private double distance = 0.0;
	int cpt =0;
	
	private GeoPoint pointA ;
	
	
	private MyLocationOverlay myLocation = null;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mapView = (MapView) this.findViewById(R.id.mapView);
		mapView.setBuiltInZoomControls(true);
		
		myLocation = new MyLocationOverlay(getApplicationContext(), mapView);
		ajouter ma localisation dans la map
		mapView.getOverlays().add(myLocation);
		afficher la localisation
		myLocation.enableMyLocation();	
		 ne plus afficher la localusation
		//myLocation.disableMyLocation();
		
		afficher la bousole
		myLocation.enableCompass();
		retirer la bousole
		//myLocation.disableCompass();
		
		mc = mapView.getController();
		mc.setZoom(17);
		
		
		Drawable drawable = this.getResources().getDrawable(R.drawable.epingle);
		ItimizedOverlay itemizedoverlay = new ItimizedOverlay(drawable,this);

		GeoPoint geoPoint = new GeoPoint(48000000, 20000000);
		OverlayItem overlayitem = new OverlayItem(geoPoint, "Hello from", "Paris");
		itemizedoverlay.addOverlayItem(overlayitem);
		
		GeoPoint geoPoint2 = new GeoPoint(-17528941, -149826891);
		OverlayItem overlayitem2 = new OverlayItem(geoPoint2, "Hello from", "Moorea");
		itemizedoverlay.addOverlayItem(overlayitem2);
		
		List<Overlay> mapOverlays = mapView.getOverlays();
		mapOverlays.add(itemizedoverlay);
		
		Route route = directions(new GeoPoint((int)(26.2*1E6),(int)(50.6*1E6)), new GeoPoint((int)(26.3*1E6),(int)(50.7*1E6)));
		RouteOverlay routeOverlay = new RouteOverlay(route, Color.RED);
		mapView.getOverlays().add(routeOverlay);
		mapView.invalidate();
	
	}

	
	@Override
	protected void onResume() {
		super.onResume();
		lm = (LocationManager) this.getSystemService(LOCATION_SERVICE);
		if (lm.isProviderEnabled(LocationManager.GPS_PROVIDER))
			lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 0,
					this);
		lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10000, 0,
				this);
	}

	@Override
	protected void onPause() {
		super.onPause();
		lm.removeUpdates(this);
	}
	

 distance entre 2 points
 *  sqrt ((longA - longB)2 + (latA_ latB)2 ) * 111,16
 
 
  */
 
@SuppressLint("SimpleDateFormat")
public class MainActivity extends FragmentActivity  implements LocationListener /*, TabListener*//*implements
	GooglePlayServicesClient.ConnectionCallbacks,
	GooglePlayServicesClient.OnConnectionFailedListener */{
	
	//optenir la date
		SimpleDateFormat Date = new SimpleDateFormat("yyyy/MM/dd");
		String date;
		 
	//optenir le temps
		SimpleDateFormat Time = new SimpleDateFormat("HH:mm:ss");
		String time ;
	
		public Calendar c = Calendar.getInstance();
		private MapView mapView;
		private MapController mc;
		
		//variable pour la base de donnée
		private DBAdapter db;
		private Geoloc geoloc;
		private Scan scan;
		private Log log;
		private Device device;
		private int geoid =0;
		
		private LocationManager lm;
		
		private double latitude;
		private double longitude;
		private double latA;
		private double longA;
		private double altitude;
		private double accuracy;
		private double distance = 0.0;
		int cpt =0;
		MarkerOptions markerOptions;
		
		GoogleMapOptions options = new GoogleMapOptions();

		
		private GeoPoint pointA ;
		public double latLngInit[] = new double[2];
		public double latLng[] = new double [2] ;
		public LatLng Lat;
		
		private MyLocationOverlay myLocation = null;
		
		double distanceaff =0.0;

	    private GoogleMap map;
	    
	    private int coul_ligne = Color.CYAN;
	    private int epaiseur_ligne = 4;
	    
	    private int nb_marqueur = 0;
	    
	    private Chronometer chronometer;
	    
	
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        
	        setContentView(R.layout.activity_main);
	       // mLocationClient = new LocationClient(this, this, this);

	        chronometer = (Chronometer) findViewById(R.id.chronometer);
	        
	        // Get a handle to the Map Fragment
	        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
	        if (map == null) {
				Log.v("JMF", "map == NULL");
			}

	        // Zoom in the Google Map
	        map.animateCamera(CameraUpdateFactory.zoomTo(19));
	        
	        map.setMyLocationEnabled(true);
	        
	     // Sets the map type to be "hybrid"
	        map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
	        
	       
	        options.mapType(GoogleMap.MAP_TYPE_HYBRID)
	        .compassEnabled(true)
	        .rotateGesturesEnabled(true)
	        .tiltGesturesEnabled(true);

            // Getting LocationManager object from System Service LOCATION_SERVICE
            LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

            // Creating a criteria object to retrieve provider
            Criteria criteria = new Criteria();

            // Getting the name of the best provider
            String provider = locationManager.getBestProvider(criteria, true);

            // Getting Current Location
            Location location = locationManager.getLastKnownLocation(provider);

            if(location!=null){
                onLocationChanged(location);
            }

            
            
            locationManager.requestLocationUpdates(provider, 30000, 0, (LocationListener) this);
            latLngInit = getlocation();
            
            //creation et ouverture de la base
           //db.open();
           //db = new DBAdapter (this , "test" );
            
            
            
           /* Polyline line = map.addPolyline(new PolylineOptions()
            .add(new LatLng(51.5, -0.1), new LatLng(latLngInit[0], latLngInit[1]))
            .width(5)
            .color(coul_ligne));
            
           /* Polygon polygon = map.addPolygon(new PolygonOptions()
            .add(new LatLng(0, 0), new LatLng(0, 5), new LatLng(3, 5), new LatLng(0, 0))
            .strokeColor(Color.RED)
            .fillColor(Color.BLUE));*/

        }
    /*

	 @Override
	    public void onTabSelected(Tab tab, android.app.FragmentTransaction ft) {
		// Toast.makeText(this, tab.getText() + " selected", Toast.LENGTH_SHORT).show();
		if(tab.getText().equals(carte.getName())){
		    ft.replace(R.id.fragmentContainer , carte);
		    setContentView(R.layout.activity_main);
		}
		else if(tab.getText().equals(graph.getName())){
		    ft.replace(R.id.fragmentContainer, graph);
		}
		
	 }
	 
	  @Override
	    public void onTabUnselected(Tab tab, android.app.FragmentTransaction ft) {
		// Toast.makeText(this, tab.getText() + " unselected", Toast.LENGTH_SHORT).show();
		if(tab.getText().equals(carte.getName())){
		    ft.remove(carte);
		}
		else if(tab.getText().equals(graph.getName())){
		    ft.remove(graph);
		}
	  }
	  
	  @Override
	    public void onTabReselected(Tab tab, android.app.FragmentTransaction ft) {
	  	Toast.makeText(this, tab.getText() + " unselected", Toast.LENGTH_SHORT).show();
	    }
	 
	*/ 
    @Override
    public void onLocationChanged(Location location) {
    	
    	//recuperer la date et l'heure
    	date = Date.format(new Date());	    	
    	time = Time.format(new Date());
    	
    	//afficher la postition
        TextView tvLocation = (TextView) findViewById(R.id.pos);

        //afficher la distance
        TextView dist = (TextView) findViewById(R.id.km);
        
        //afficher le nb de marqueur
        TextView marq = (TextView) findViewById(R.id.marqueur);
        
        // Getting latitude of the current location
        double latitude = location.getLatitude();

        // Getting longitude of the current location
        double longitude = location.getLongitude();
        
        accuracy = location.getAccuracy();
        
        Toast.makeText(getBaseContext(), "precision " + accuracy + " mètres", Toast.LENGTH_LONG).show();
        
        
        geoloc = new Geoloc(geoid , latitude, longitude);
        //db.insererGEOLOC(longitude, latitude, date);
        

        
        // Creating a LatLng object for the current location
        latLng[0] = latitude;
        latLng[1] = longitude;
       
        LatLng debut = new LatLng (latLngInit[0], latLngInit[1]);
        Lat = new LatLng (latitude, longitude);
        
		// Showing the current location in Google Map
        map.moveCamera(CameraUpdateFactory.newLatLng(Lat));

        // Zoom in the Google Map
        map.animateCamera(CameraUpdateFactory.zoomTo(17));
        
        //ajoueter un marqueur sur la position de l'utilisateur
        map.addMarker(new MarkerOptions()
        .position(new LatLng(latitude, longitude))
        .title("Latitude:" +  latitude  + ", Longitude:"+ longitude )
        .alpha(0.7f)
        .snippet("J'étais ici le :\n"
        		+ date + " à " + time));
        //.icon(BitmapDescriptorFactory.fromResource(R.drawable.position)));

        nb_marqueur++;
        marq.setText(" " + nb_marqueur);

        // Setting latitude and longitude in the TextView tv_location
        tvLocation.setText("Latitude:" +  latitude  + ", Longitude:"+ longitude );
        
        if (cpt == 0) {
        	dist.setText(" " + distanceaff);
        	cpt = 1;	
        }
        else {
        distanceaff += Getdistance (latLngInit[0] ,latitude, latLngInit[1] , longitude);
        // Setting distance in the TextViex dist
        dist.setText(" " + distanceaff);
        
        
      //tacer le chemin sur la carte
        Polyline chemin = map.addPolyline(new PolylineOptions()
        .add(debut, Lat)
        .width(epaiseur_ligne)
        .color(coul_ligne));
        }
        
        latLngInit[0] = latitude;
        latLngInit[1] = longitude;
    }

    public double[] getlocation() {
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        List<String> providers = lm.getProviders(true);

        Location l = null;
        for (int i = 0; i < providers.size(); i++) {
            l = lm.getLastKnownLocation(providers.get(i));
            if (l != null)
                break;
        }
        double[] gps = new double[2];

        if (l != null) {
            gps[0] = l.getLatitude();
            gps[1] = l.getLongitude();
        }
        return gps;
    }
    
    
	 @Override
	  public boolean onCreateOptionsMenu(Menu menu) {
	    getMenuInflater().inflate(R.menu.main, menu);
	    return true;
	  }


	 
		@Override
		public boolean onOptionsItemSelected(MenuItem item) {
			String msg ="";
			switch (item.getItemId()) {
			
			//changer le type de carte
			case R.id.hybrid:
				// Sets the map type to be "hybrid"
		        map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
		        msg = "Carte hybride";
		        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
				return true;
			case R.id.normal:
				// Sets the map type to be "hybrid"
		        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		        msg = "Carte Normale";
		        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
				return true;
			case R.id.none:
				// Sets the map type to be "hybrid"
		        map.setMapType(GoogleMap.MAP_TYPE_NONE);
		        msg = "Carte vide";
		        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
				return true;
			case R.id.satellite:
				// Sets the map type to be "hybrid"
		        map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
		        msg = "Carte Satellite";
		        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
				return true;
			case R.id.terrain:
				// Sets the map type to be "hybrid"
		        map.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
		        msg = "Carte Terrain";
		        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
				return true;
			
			//changer la couleur du chemin
			case R.id.blue:
				// Sets the map color line to blue
		        coul_ligne = Color.CYAN;
		         msg = "Trait Bleue";
		        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
				return true;
			case R.id.red:
				// Sets the map color ligne to red
				coul_ligne = Color.RED;
				 msg = "Trait Rouge";
			        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
				return true;
			case R.id.green:
				// Sets the map type to be "hybrid"
				coul_ligne = Color.GREEN;
				 msg = "Trait Vert";
			        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
				return true;
			case R.id.black:
				// Sets the map type to be "hybrid"
				coul_ligne = Color.BLACK;
				msg = "Trait Noir";
		        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
				return true;
				
			//changer l'epaisseur du trait	
			case R.id.petit:
				// Sets the map color line to blue
		        epaiseur_ligne = 1;
		        msg = "Petit Trait";
		        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
				return true;
			case R.id.moyen:
				// Sets the map color ligne to red
				epaiseur_ligne = 4;
				msg = "Trait moyen";
		        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
				return true;
			case R.id.gros:
				// Sets the map type to be "hybrid"
				epaiseur_ligne = 7;
				msg = "Trait epais";
		        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
				return true;
			case R.id.epais:
				// Sets the map type to be "hybrid"
				epaiseur_ligne = 10;
				msg = " Gros Trait";
		        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
				return true;
				
				
				
			default:
				return super.onOptionsItemSelected(item);
			}
		}
		
		
		
		//retourne la distance entre 2 points au metre pres
		public double Getdistance (double latA,double latB, double longA ,double longB) {
			/*double distance
			distance = Math.sqrt (  (longA - longB)*(longA - longB) + (latA * latB)*(latA - latB)) * 111.16;
			
			*/
			double R = 6371; // Radius of the earth in km
			  double dLat = deg2rad(latB-latA);  // deg2rad below
			  double dLon = deg2rad(longB-longA); 
			  double a = 
			    Math.sin(dLat/2) * Math.sin(dLat/2) +
			    Math.cos(deg2rad(latA)) * Math.cos(deg2rad(latB)) * 
			    Math.sin(dLon/2) * Math.sin(dLon/2)
			    ; 
			 double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)); 
			  double d = R * c; // Distance in km
			  
			  if (d < 0.001)
				  return 0.0;
			  else {
				  return (( (int)(d*1000))/1000.);
			  }
		}
		public double deg2rad (double deg) {
			  return deg * (Math.PI/180);
			}

		@Override
		public void onProviderDisabled(String provider) {
			/*String msg = String.format(
			getResources().getString(R.string.provider_disabled), provider);
			Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();	*/
			
		}


		@Override
		public void onProviderEnabled(String provider) {
			/*String msg = String.format(
			getResources().getString(R.string.provider_enabled), provider);
			Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();*/
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		String newStatus = "";
		switch (status) {
		case LocationProvider.OUT_OF_SERVICE:
			newStatus = "OUT_OF_SERVICE";
			break;
		case LocationProvider.TEMPORARILY_UNAVAILABLE:
			newStatus = "TEMPORARILY_UNAVAILABLE";
			break;
		case LocationProvider.AVAILABLE:
			newStatus = "AVAILABLE";
			break;
		}
		String msg = String.format(
				getResources().getString(R.string.provider_disabled), provider,
				newStatus);
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}
	
	public void start(View v) {
		chronometer.setBase(SystemClock.elapsedRealtime());
		chronometer.start();
		String msg ="activation du chronometre";
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}
	
	
	public void stop(View v) {
		chronometer.stop();
		String msg = "arret du chronometre";
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}
	
	
	public void reset (View v) {
	
		 //afficher la distance
        TextView dist = (TextView) findViewById(R.id.km);
		
		distanceaff = 0;
        // Setting distance in the TextViex dist
        dist.setText(" " + distanceaff);
		
		String msg = "distance remise à zero";
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
		
		
		
	}
	
	
}

