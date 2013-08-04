package socialtoilet.android.location;

import java.util.ArrayList;
import java.util.Collection;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;

public class GPSTracker extends Service implements LocationListener
{
	private static GPSTracker instance;
	
	public static void initialize(Context context)
	{
		instance = new GPSTracker(context);
	}
	public static GPSTracker getInstance()
	{
		return instance;
	}
	
	private Collection<IGPSTrakerListener> listeners;
	
    private final Context context;
    
    // flag for GPS status
    private boolean isGPSEnabled = false;
 
    // flag for network status
    private boolean isNetworkEnabled = false;
 
    // flag for GPS status
    private boolean canGetLocation = false;
 
    private Location location; // location
    private double latitude; // latitude
    private double longitude; // longitude
 
    // The minimum distance to change Updates in meters
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 50; // 10 meters
 
    // The minimum time between updates in milliseconds
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1 minute
 
    // Declaring a Location Manager
    protected LocationManager locationManager;
 
    private GPSTracker(Context context)
    {
    	this.listeners = new ArrayList<IGPSTrakerListener>();
        this.context = context;
        getLocation();
    }

    public Location getLocation()
    {
    	try
        {
            locationManager = (LocationManager) context
                    .getSystemService(LOCATION_SERVICE);
 
            // getting GPS status
            isGPSEnabled = locationManager
                    .isProviderEnabled(LocationManager.GPS_PROVIDER);
 
            // getting network status
            isNetworkEnabled = locationManager
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER);
 
            if (!isGPSEnabled && !isNetworkEnabled)
            {
                // no network provider is enabled
            }
            else
            {
                this.canGetLocation = true;
                // First get location from Network Provider
                if (isNetworkEnabled) {
                    locationManager.requestLocationUpdates(
                            LocationManager.NETWORK_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                    Log.d("Network", "Network");
                    if (locationManager != null)
                    {
                        location = locationManager
                                .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        if (location != null)
                        {
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                        }
                    }
                }
                // if GPS Enabled get lat/long using GPS Services
                if (isGPSEnabled)
                {
                    if (location == null)
                    {
                        locationManager.requestLocationUpdates(
                                LocationManager.GPS_PROVIDER,
                                MIN_TIME_BW_UPDATES,
                                MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                        Log.d("GPS Enabled", "GPS Enabled");
                        if (locationManager != null)
                        {
                            location = locationManager
                                    .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            if (location != null)
                            {
                                latitude = location.getLatitude();
                                longitude = location.getLongitude();
                            }
                        }
                    }
                }
            }
 
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
 
        return location;
    }
    
    /**
     * Stop using GPS listener
     * Calling this function will stop using GPS in your app
     * */
    public void stopUsingGPS()
    {
        if(locationManager != null)
        {
            locationManager.removeUpdates(GPSTracker.this);
        }       
    }
    
    /**
     * Function to get latitude
     * */
    public double getLatitude()
    {
    	if(null != location)
    	{
    		latitude = location.getLatitude();
    	}
    	return latitude;
    }
    
    /**
     * Function to get longitude
     * */
    public double getLongitude()
    {
    	if(null != location)
    	{
    		longitude = location.getLongitude();
    	}
    	return longitude;
    }
    
    /**
     * Function to check GPS/wifi enabled
     * @return boolean
     * */
    public boolean canGetLocation()
    {
    	return this.canGetLocation;
    }
    
   /**
    * Function to show settings alert dialog
    * On pressing Settings button will lauch Settings Options
    * */
    public void showSettingsAlert()
    {
    	AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
     
    	// Setting Dialog Title
    	alertDialog.setTitle("GPS is settings");
 
    	// Setting Dialog Message
    	alertDialog.setMessage("GPS is not enabled. Do you want to go to settings menu?");
 
    	// On pressing Settings button
    	alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener()
    	{
    		public void onClick(DialogInterface dialog,int which)
    		{
    			Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
    			context.startActivity(intent);
    		}
    	});
 
    	// on pressing cancel button
    	alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
    	{
    		public void onClick(DialogInterface dialog, int which)
    		{
    			dialog.cancel();
    		}
    	});
 
    	alertDialog.show();
   }

    public void addChangeLocationListener(IGPSTrakerListener listener)
    {
    	if( null == listeners )
    	{
    		return;
    	}
    	if( false == listeners.contains(listener) )
    	{
    		listeners.add(listener);
    	}
    }
    
    public void removeChangeLocationListener(IGPSTrakerListener listener)
    {
    	if( null == listeners )
    	{
    		return;
    	}
    	if( listeners.contains(listener) )
    	{
    		listeners.remove(listener);
    	}
    }
    
	@Override
	public void onLocationChanged(Location location)
	{
		if(null == listeners)
		{
			return;
		}
		for( IGPSTrakerListener listener : listeners )
		{
			listener.locationChange(location);
		}
	}

	@Override
	public void onProviderDisabled(String arg0) {}

	@Override
	public void onProviderEnabled(String arg0) {}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {}

	@Override
	public IBinder onBind(Intent arg0)
	{
		return null;
	}

}
