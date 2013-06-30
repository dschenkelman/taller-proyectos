package com.example.appsample;

import java.util.Collection;

import com.example.appsample.location.GPSTracker;
import com.example.appsample.location.IGPSTrakerDelegate;
import com.example.appsample.model.IToilet;
import com.example.appsample.services.IRetreiveToiletsService;
import com.example.appsample.services.IRetreiveToiletsServiceDelegate;
import com.example.appsample.services.mocks.MockRetrieveToiletsService;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;

import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class DisplayNearToiletsActivity extends FragmentActivity 
	implements IRetreiveToiletsServiceDelegate, IGPSTrakerDelegate
{
	private GoogleMap map;
	private GPSTracker gps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_near_toilets);
        
        setUpMapIfNeeded();
    }
    
    private void retrieveNearBathrooms(double latitude, double longitude)
    {
    	IRetreiveToiletsService service = new MockRetrieveToiletsService();
    	service.retrieveNearBathrooms(latitude, longitude, this);
	}

	private void setUpMapIfNeeded()
    {
        // Do a null check to confirm that we have not already instantiated the map.
        if(null == map)
        {
            map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
            map.setMyLocationEnabled(true);
            // Check if we were successful in obtaining the map.
            if(null != map)
            { // The Map is verified. It is now safe to manipulate the map.
            	setupGPSLocation();
            }
        }
        else
        {
        	setupGPSLocation();
        }
    }
    
    private void setupGPSLocation()
    {
    	if(null == gps)
    	{
    		gps = new GPSTracker(getApplicationContext(), this);
    	}
        // check if GPS enabled     
        if(gps.canGetLocation())
        {
        	double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();
            
            Toast.makeText(getApplicationContext(), "Your Location is:\nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show(); 

            LatLng coordinate = new LatLng(latitude, longitude);
            CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(coordinate, 14);
            map.moveCamera(yourLocation);
            retrieveNearBathrooms(latitude, longitude);
        }else{
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
            Toast.makeText(getApplicationContext(), "Default location: FIUBA (-34.617024, -58.368512)", Toast.LENGTH_LONG).show(); 
            LatLng coordinate = new LatLng(-34.617024, -58.368512);
            CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(coordinate, 15);
            map.moveCamera(yourLocation);
            retrieveNearBathrooms(-34.617024, -58.368512);
        }
    }

	@Override
	public void retreiveNearToiletsFinish(IRetreiveToiletsService service,
			Collection<IToilet> nearBathrooms)
	{
		if(null == map )
		{
			return;
		}
		for(IToilet bathroom : nearBathrooms)
		{
			map.addMarker(new MarkerOptions()
	        .position(new LatLng(bathroom.getLatitude(), bathroom.getLongitude()))
	        .title(bathroom.getMapTitle())
	        .snippet(bathroom.getMapSnippet()));
		}
	}

	@Override
	public void retreiveNearToiletsFinishWithError(
			IRetreiveToiletsService service, int errorCode)
	{
		Toast.makeText(getApplicationContext(), "Error retrieving the toilets", Toast.LENGTH_LONG).show();
	}
	
	@Override
	public void locationChange(Location location)
	{
    	double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        
        Toast.makeText(getApplicationContext(), "New Location:\nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show(); 

        LatLng coordinate = new LatLng(latitude, longitude);
        CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(coordinate, 14);
        map.moveCamera(yourLocation);
        retrieveNearBathrooms(latitude, longitude);
	}

}
