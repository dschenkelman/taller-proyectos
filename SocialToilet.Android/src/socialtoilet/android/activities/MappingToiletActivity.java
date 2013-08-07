package socialtoilet.android.activities;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import socialtoilet.android.R;
import socialtoilet.android.activities.dialogs.AddToiletDialogFragment;
import socialtoilet.android.activities.dialogs.AddToiletDialogFragment.INoticeDialogAddToiletListener;
import socialtoilet.android.location.GPSTracker;
import socialtoilet.android.location.IGPSTrakerListener;
import socialtoilet.android.model.IToilet;
import socialtoilet.android.model.IToiletCreatedDelegate;
import socialtoilet.android.services.factories.ServicesFactory;
import socialtoilet.android.services.get.IRetrieveNearToiletsService;
import socialtoilet.android.services.get.IRetrieveNearToiletsServiceDelegate;
import socialtoilet.android.services.get.RetrieveNearToiletsService;
import socialtoilet.android.utils.Settings;
import socialtoilet.android.utils.StateSaver;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

public class MappingToiletActivity extends FragmentActivity
	implements IRetrieveNearToiletsServiceDelegate, IGPSTrakerListener,
		OnInfoWindowClickListener, INoticeDialogAddToiletListener,
		IToiletCreatedDelegate
{

	public final static String EXTRA_TOILET_ID = "socialtoilet.andorid.mappingtoilet.TOILET";
	public final static String EXTRA_LATITUDE = "socialtoilet.andorid.mappingtoilet.LATITUDE";
	public final static String EXTRA_LONGITUDE = "socialtoilet.andorid.mappingtoilet.LONGITUDE";
	public final static String TOILET_CREATED_DELEGATE = "socialtoilet.andorid.mappingtoilet.TOILET_CREATED_DELEGATE";
	
	private GoogleMap map;
	private GPSTracker gps;
	private int radialDistanceInMeters;
	private LatLng manualSelectionMapUbication;
	
	private Map<String, IToilet> dictionary;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mapping_toilet);
		// Show the Up button in the action bar.

		GPSTracker.initialize(getApplicationContext());
		
		radialDistanceInMeters = Settings.getInstance().getInitialRadiusInMeters();
        dictionary = new HashMap<String, IToilet>();
        
        setUpMapIfNeeded();
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
			NavUtils.navigateUpFromSameTask(this);
			return true;
		case R.id.action_add_toilet:
			onAddToiletButtonTapped();
	    	return true;
		case R.id.action_exit:
			onCloseButtonTapped();
	    	return true;
		case R.id.action_settings:
	        startActivity(new Intent(this, SettingsActivity.class));
			return true;
		case R.id.action_update:
			oncUpdateButtonTapped();
			return true;
		}
		return super.onOptionsItemSelected(item);
	} 
	
    private void oncUpdateButtonTapped()
    {
		radialDistanceInMeters = Settings.getInstance().getInitialRadiusInMeters();
    	map.clear();
    	
    	double latitude = gps.getLatitude();
        double longitude = gps.getLongitude();
        
        retrieveNearToilets(latitude, longitude);
	}


	private void onCloseButtonTapped()
    {
    	Settings.getInstance().logoutUser();
     	Intent intent = new Intent(this, StartSessionActivity.class);
     	intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);	
	}

	private void onAddToiletButtonTapped()
	{
	 	Intent intent = new Intent(this, AddToiletActivity.class);
	 	intent.putExtra(EXTRA_LATITUDE, GPSTracker.getInstance().getLatitude());
	 	intent.putExtra(EXTRA_LONGITUDE, GPSTracker.getInstance().getLongitude());
	 	StateSaver.getInstance().save(TOILET_CREATED_DELEGATE, this);
	    startActivity(intent);	
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
            	
              	map.setOnMapLongClickListener(new OnMapLongClickListener() {
            		
              		@Override
    					public void onMapLongClick(LatLng point) {
    						onLongClickAddToiletButtonTapped(point);
    					}
    				});
              	
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
            LatLng coordinate = new LatLng(-34.617024, -58.368512);
            CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(coordinate, 15);
            map.moveCamera(yourLocation);
            retrieveNearToilets(-34.617024, -58.368512);
        }
    }
    
    private void retrieveNearToilets(double latitude, double longitude)
    {
    	IRetrieveNearToiletsService service = ServicesFactory.createRetrieveNearToiletsService();//new RetrieveNearToiletsService();
    	service.retrieveNearToilets(latitude, longitude, radialDistanceInMeters, this);
	}
    
	@Override
	public void retrieveNearToiletsFinish(IRetrieveNearToiletsService service,
			Collection<IToilet> nearToilets)
	{

		if(null == map )
		{
			return;
		}
		dictionary.clear();
		for(IToilet toilet : nearToilets)
		{
			addTOiletToMap(toilet);
		}
	}
	
	private void addTOiletToMap(IToilet toilet)
	{
		LatLng mapPosition = new LatLng(toilet.getLatitude(), toilet.getLongitude());
		MarkerOptions markerOptions = new MarkerOptions()
        	.position(mapPosition)
        	.title(toilet.getMapTitle())
        	
       
        	.icon(BitmapDescriptorFactory.fromResource(R.drawable.socialmarker))
        	
        	.snippet(toilet.getMapSnippet());
		
		Marker marker = map.addMarker(markerOptions);
		dictionary.put(marker.getTitle()+marker.getPosition().latitude+marker.getPosition().longitude, toilet);
	}


	@Override
	public void retreiveNearToiletsFinishWithError(
			IRetrieveNearToiletsService service, String errorCode)
	{
		if( errorCode.equalsIgnoreCase(RetrieveNearToiletsService.emptyResponseErrorType) )
		{
			radialDistanceInMeters = 2 * radialDistanceInMeters;
			retrieveNearToilets(gps.getLatitude(), gps.getLongitude());
		}
		
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder.setTitle("Your Title");
 
		alertDialogBuilder
			.setMessage("Click yes to exit!")
			.setCancelable(false)
			.setPositiveButton("Yes", new DialogInterface.OnClickListener()
				{
					public void onClick(DialogInterface dialog,int id)
					{
						// if this button is clicked, close
						// current activity
					}
				})
			.setNegativeButton("No",new DialogInterface.OnClickListener()
				{
					public void onClick(DialogInterface dialog,int id) {
						// if this button is clicked, just close
						// the dialog box and do nothing
						dialog.cancel();
					}
				});
 
		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();
	}

	@Override
	public void locationChange(Location location)
	{
    	double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        
        LatLng coordinate = new LatLng(latitude, longitude);
        CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(coordinate, 14);
        map.moveCamera(yourLocation);
        retrieveNearToilets(latitude, longitude);
	}

	@Override
	public void onInfoWindowClick(Marker marker)
	{
		IToilet toilet = dictionary.get(marker.getTitle()+marker.getPosition().latitude+marker.getPosition().longitude);
		
		StateSaver.getInstance().saveToilet(EXTRA_TOILET_ID, toilet);
		Intent intent = new Intent(this, DetailToiletActivity.class);
		intent.putExtra(EXTRA_TOILET_ID, toilet.getID().toString());
		startActivity(intent);
	}

	protected void onLongClickAddToiletButtonTapped(LatLng point) {
    	AddToiletDialogFragment dialog = new AddToiletDialogFragment();
    	dialog.show(getSupportFragmentManager(), "dialog");
    	manualSelectionMapUbication=point;
	}

	@Override
	public void onDialogPositiveClick(DialogFragment dialog)
	{
	 	Intent intent = new Intent(this, AddToiletActivity.class);
	 	intent.putExtra(EXTRA_LATITUDE, manualSelectionMapUbication.latitude);
	 	intent.putExtra(EXTRA_LONGITUDE, manualSelectionMapUbication.longitude);
	 	StateSaver.getInstance().save(TOILET_CREATED_DELEGATE, this);
	    startActivity(intent);
	}


	@Override
	public void toiletCreated(IToilet toilet)
	{
		addTOiletToMap(toilet);
    	double latitude = toilet.getLatitude();
        double longitude = toilet.getLongitude();
        
        LatLng coordinate = new LatLng(latitude, longitude);
        CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(coordinate, 14);
        map.moveCamera(yourLocation);
	}
}
