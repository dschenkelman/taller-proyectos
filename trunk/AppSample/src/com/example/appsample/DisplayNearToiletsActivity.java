package com.example.appsample;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.example.appsample.location.GPSTracker;
import com.example.appsample.location.IGPSTrakerDelegate;
import com.example.appsample.model.IToilet;
import com.example.appsample.services.IRetrieveToiletsService;
import com.example.appsample.services.IRetrieveToiletsServiceDelegate;
import com.example.appsample.services.mocks.MockRetrieveToiletsService;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class DisplayNearToiletsActivity extends FragmentActivity 
	implements IRetrieveToiletsServiceDelegate, IGPSTrakerDelegate,
	OnInfoWindowClickListener
{
	public final static String EXTRA_TOILET_ID = "com.example.appsample.TOILET";
	
	private GoogleMap map;
	private GPSTracker gps;
	
	private Map<Marker, IToilet> dictionary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_near_toilets);
        dictionary = new HashMap<Marker, IToilet>();
        
        setUpMapIfNeeded();
    }
    
    private void retrieveNearBathrooms(double latitude, double longitude)
    {
    	IRetrieveToiletsService service = new MockRetrieveToiletsService();
    	service.retrieveNearToilet(latitude, longitude, this);
	}

	private void setUpMapIfNeeded()
    {
        // Do a null check to confirm that we have not already instantiated the map.
        if(null == map)
        {
            map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
            map.setMyLocationEnabled(true);
            map.setOnInfoWindowClickListener(this);
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
            
            Toast.makeText(getApplicationContext(), "Your Location is:\nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_SHORT).show(); 

            LatLng coordinate = new LatLng(latitude, longitude);
            CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(coordinate, 14);
            map.moveCamera(yourLocation);
            retrieveNearBathrooms(latitude, longitude);
        }else{
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
            Toast.makeText(getApplicationContext(), "Default location: FIUBA (-34.617024, -58.368512)", Toast.LENGTH_SHORT).show(); 
            LatLng coordinate = new LatLng(-34.617024, -58.368512);
            CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(coordinate, 15);
            map.moveCamera(yourLocation);
            retrieveNearBathrooms(-34.617024, -58.368512);
        }
    }

	@Override
	public void retrieveNearToiletsFinish(IRetrieveToiletsService service,
			Collection<IToilet> nearBathrooms)
	{
		if(null == map )
		{
			return;
		}
		dictionary.clear();
		for(IToilet toilet : nearBathrooms)
		{
			MarkerOptions markerOptions = new MarkerOptions()
	        	.position(new LatLng(toilet.getLatitude(), toilet.getLongitude()))
	        	.title(toilet.getMapTitle())
	        	.snippet(toilet.getMapSnippet());
			
			Marker marker = map.addMarker(markerOptions);
			dictionary.put(marker, toilet);
		}
	}

	@Override
	public void retreiveNearToiletsFinishWithError(
			IRetrieveToiletsService service, int errorCode)
	{
		Toast.makeText(getApplicationContext(), "Error retrieving the toilets", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void retrieveToiletFinish(IRetrieveToiletsService mockRetrieveToiletsService, IToilet toilet) {}

	@Override
	public void retrieveToiletFinishWithError(IRetrieveToiletsService mockRetrieveToiletsService, int errorCode) {}

	@Override
	public void locationChange(Location location)
	{
    	double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        
        Toast.makeText(getApplicationContext(), "New Location:\nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_SHORT).show(); 

        LatLng coordinate = new LatLng(latitude, longitude);
        CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(coordinate, 14);
        map.moveCamera(yourLocation);
        retrieveNearBathrooms(latitude, longitude);
	}

	@Override
	public void onInfoWindowClick(Marker marker)
	{
		IToilet toilet = dictionary.get(marker.getId());
		if( null == toilet )
		{
			
			Intent intent = new Intent(this, DetailToiletActivity.class);
			intent.putExtra(EXTRA_TOILET_ID, "44e128a5-ac7a-4c9a-be4c-224b6bf81b20");
		    startActivity(intent);
			
			Toast.makeText(getApplicationContext(), "Error: can't show the detail view", Toast.LENGTH_SHORT).show();
		}
		else
		{
			Intent intent = new Intent(this, DetailToiletActivity.class);
			intent.putExtra(EXTRA_TOILET_ID, toilet.getID().toString());
		    startActivity(intent);
		}
	}

}
