package socialtoilet.android;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import socialtoilet.android.location.GPSTracker;
import socialtoilet.android.location.IGPSTrakerListener;
import socialtoilet.android.model.IToilet;
import socialtoilet.android.services.IRetrieveNearToiletsService;
import socialtoilet.android.services.IRetrieveNearToiletsServiceDelegate;
import socialtoilet.android.services.RetrieveNearToiletsService;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;

public class MappingToiletActivity extends FragmentActivity
	implements IRetrieveNearToiletsServiceDelegate, IGPSTrakerListener, OnInfoWindowClickListener
{
	
	public final static String EXTRA_TOILET_ID = "com.example.appsample.TOILET";
	
	private GoogleMap map;
	private GPSTracker gps;
	private int radialDistanceInMeters;
	
	private Map<String, IToilet> dictionary;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mapping_toilet);
		// Show the Up button in the action bar.
		setupActionBar();
		
		radialDistanceInMeters = 10000;
        dictionary = new HashMap<String, IToilet>();
        
        setUpMapIfNeeded();
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar()
	{
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
		{
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mapping_toilet, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	} 
	
    public void onDestroy()
    {
    	gps.removeChangeLocationListener(this);
        super.onDestroy();
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
    		gps = GPSTracker.getInstance();
    		gps.addChangeLocationListener(this);
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
          
            retrieveNearToilets(latitude, longitude);
        }
        else
        {
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
            Toast.makeText(getApplicationContext(), "Default location: FIUBA (-34.617024, -58.368512)", Toast.LENGTH_SHORT).show(); 
            LatLng coordinate = new LatLng(-34.617024, -58.368512);
            CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(coordinate, 15);
            map.moveCamera(yourLocation);
            retrieveNearToilets(-34.617024, -58.368512);
        }
    }
    
    private void retrieveNearToilets(double latitude, double longitude)
    {
    	IRetrieveNearToiletsService service = new RetrieveNearToiletsService();
    	service.retrieveNearToilets(latitude, longitude, radialDistanceInMeters, this);
	}
    
	@Override
	public void retrieveNearToiletsFinish(IRetrieveNearToiletsService service,
			Collection<IToilet> nearToilets) {

		if(null == map )
		{
			return;
		}
		dictionary.clear();
		for(IToilet toilet : nearToilets)
		{
			LatLng mapPosition = new LatLng(toilet.getLatitude(), toilet.getLongitude());
			MarkerOptions markerOptions = new MarkerOptions()
	        	.position(mapPosition)
	        	.title(toilet.getMapTitle())
	        	.snippet(toilet.getMapSnippet());
			
			Marker marker = map.addMarker(markerOptions);
			dictionary.put(marker.getTitle()+marker.getPosition().latitude+marker.getPosition().longitude, toilet);
		}
	}
	
	@Override
	public void retreiveNearToiletsFinishWithError(
			IRetrieveNearToiletsService service, String errorCode)
	{
		Toast.makeText(getApplicationContext(), errorCode, Toast.LENGTH_SHORT).show();
		
		if( errorCode.equalsIgnoreCase(RetrieveNearToiletsService.emptyResponseErrorType) )
		{
			radialDistanceInMeters = 2 * radialDistanceInMeters;
			retrieveNearToilets(gps.getLatitude(), gps.getLongitude());
		}
	}

	@Override
	public void locationChange(Location location)
	{
    	double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        
        Toast.makeText(getApplicationContext(), "New Location:\nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_SHORT).show(); 

        LatLng coordinate = new LatLng(latitude, longitude);
        CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(coordinate, 14);
        map.moveCamera(yourLocation);
        retrieveNearToilets(latitude, longitude);
	}

	@Override
	public void onInfoWindowClick(Marker marker)
	{
		IToilet toilet = dictionary.get(marker.getTitle()+marker.getPosition().latitude+marker.getPosition().longitude);
		if( toilet == null )
		{
			Toast.makeText(getApplicationContext(), "No toilet", Toast.LENGTH_SHORT).show(); 
		}
		Intent intent = new Intent(this, DetailToiletActivity.class);
		intent.putExtra(EXTRA_TOILET_ID, toilet.getID().toString());
		startActivity(intent);
	}
}
