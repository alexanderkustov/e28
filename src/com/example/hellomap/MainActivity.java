package com.example.hellomap;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class MainActivity extends FragmentActivity implements LocationListener {
	private GoogleMap mMap;
	private static final LatLng LISBON = new LatLng(38.70798, -9.13670);
    LatLng myPosition;

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		setUpMapIfNeeded();
		
		CameraPosition cameraPosition = new CameraPosition.Builder()
		.target(LISBON) // Sets the center of the map to Mountain View
		.zoom(17) // Sets the zoom
		.tilt(45)
		.build(); // Creates a CameraPosition from the builder

		mMap.animateCamera(CameraUpdateFactory
		.newCameraPosition(cameraPosition));
		
		myLocationPositioner();
        
        mMap.setOnInfoWindowClickListener(
				  new OnInfoWindowClickListener(){
				    public void onInfoWindowClick(Marker terpac){
				    	Context context = getApplicationContext();
				    	CharSequence text = "Isto vai abrir pagina de infos	!";
				    	int duration = Toast.LENGTH_SHORT;

				    	Toast toast = Toast.makeText(context, text, duration);
				    	toast.show();
				    }

					
				  }
				);

	}

	private void myLocationPositioner() {
		
		mMap.setMyLocationEnabled(true);
		
		// Getting LocationManager object from System Service LOCATION_SERVICE
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        // Creating a criteria object to retrieve provider
        Criteria criteria = new Criteria();

        // Getting the name of the best provider
        String provider = locationManager.getBestProvider(criteria, true);

        // Getting Current Location
        Location location = locationManager.getLastKnownLocation(provider);

        if(location!=null){
        // Getting latitude of the current location
        double latitude = location.getLatitude();

        // Getting longitude of the current location
        double longitude = location.getLongitude();

        // Creating a LatLng object for the current location
        LatLng latLng = new LatLng(latitude, longitude);

         myPosition = new LatLng(latitude, longitude);

        }
	}

	@Override
	protected void onResume() {
		super.onResume();
		setUpMapIfNeeded();
	}

	private void setUpMapIfNeeded() {
		if (mMap == null) {
			mMap = ((SupportMapFragment) getSupportFragmentManager()
					.findFragmentById(R.id.map)).getMap();

		}
		if (mMap == null) {
			return;
		}

		
		Marker terpac = mMap.addMarker(new MarkerOptions()
				.position(new LatLng(38.70798, -9.13670))
				.title("Terreiro do Paço")
				.snippet("This is da real place to be"));
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
	
	//mMap.setOnInfoWindowClickListener();
}
