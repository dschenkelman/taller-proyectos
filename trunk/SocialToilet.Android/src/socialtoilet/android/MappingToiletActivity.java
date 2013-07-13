package socialtoilet.android;

import java.util.HashMap;
import java.util.Map;

import socialtoilet.android.location.GPSTracker;
import socialtoilet.android.location.IGPSTrakerDelegate;
import socialtoilet.android.model.IToilet;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.os.Build;

public class MappingToiletActivity extends FragmentActivity
	implements IGPSTrakerDelegate, OnInfoWindowClickListener
{
	
	public final static String EXTRA_TOILET_ID = "com.example.appsample.TOILET";
	
	private GoogleMap map;
	private GPSTracker gps;
	
	private Map<Marker, IToilet> dictionary;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mapping_toilet);
		// Show the Up button in the action bar.
		setupActionBar();
		
        dictionary = new HashMap<Marker, IToilet>();
        
        setUpMapIfNeeded();
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mapping_toilet, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
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
          
            //retrieveNearBathrooms(latitude, longitude);
        }else{
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
            Toast.makeText(getApplicationContext(), "Default location: FIUBA (-34.617024, -58.368512)", Toast.LENGTH_SHORT).show(); 
            LatLng coordinate = new LatLng(-34.617024, -58.368512);
            CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(coordinate, 15);
            map.moveCamera(yourLocation);
           // retrieveNearBathrooms(-34.617024, -58.368512);
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
        //retrieveNearBathrooms(latitude, longitude);
	}

	@Override
	public void onInfoWindowClick(Marker marker)
	{
		/*IToilet toilet = dictionary.get(marker.getId());
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
		}*/
	}

}