package com.example.appsample;

import java.util.Collection;

import com.example.appsample.model.IToilet;
import com.example.appsample.services.IRetreiveToiletsService;
import com.example.appsample.services.IRetreiveToiletsServiceDelegate;
import com.example.appsample.services.mocks.MockRetrieveToiletsService;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;

import android.app.Dialog;
import android.content.IntentSender;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class DisplayNearToiletsActivity extends FragmentActivity 
	implements IRetreiveToiletsServiceDelegate
{

    /*
     * Define a request code to send to Google Play services
     * This code is returned in Activity.onActivityResult
     */
    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    
	private GoogleMap map;
	private GPSTracker gps;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_near_toilets);
        
        setUpMapIfNeeded();
        retrieveNearBathrooms();
    }
    
    private void retrieveNearBathrooms()
    {
    	IRetreiveToiletsService service = new MockRetrieveToiletsService();
    	service.retrieveNearBathrooms(this);
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
    		gps = new GPSTracker(DisplayNearToiletsActivity.this);
    	}
        // check if GPS enabled     
        if(gps.canGetLocation())
        {
        	double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();
            
            Toast.makeText(getApplicationContext(), "Your Location is:\nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show(); 

            LatLng coordinate = new LatLng(latitude, longitude);
            CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(coordinate, 13);
            map.moveCamera(yourLocation);
        }else{
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
            Toast.makeText(getApplicationContext(), "Default location: FIUBA (-34.617024, -58.368512)", Toast.LENGTH_LONG).show(); 
            LatLng coordinate = new LatLng(-34.617024, -58.368512);
            CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(coordinate, 15);
            map.moveCamera(yourLocation);
        }
    }

	@Override
	public void retreiveNearToiletsFinish(IRetreiveToiletsService service,
			Collection<IToilet> nearBathrooms)
	{
		// TODO Auto-generated method stub
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
	
	
    // A request to connect to Location Services
    private LocationRequest mLocationRequest;

    // Stores the current instantiation of the location client in this object
    private LocationClient mLocationClient;

}
