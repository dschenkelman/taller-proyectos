package socialtoilet.android.activities;

import java.util.UUID;

import com.google.ads.AdRequest;
import com.google.ads.AdView;

import socialtoilet.android.R;
import socialtoilet.android.location.GPSTracker;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		GPSTracker.initialize(getApplicationContext());
		
	    AdView adView = (AdView)this.findViewById(R.id.ad);
	    AdRequest adRequest = new AdRequest();
	    adView.loadAd(adRequest);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

    public void findNearToiletsTapped(View view)
    {
    	Intent intent = new Intent(this, MappingToiletActivity.class);
    	startActivity(intent);
    }
    
    public void addToiletHereTapped(View view)
    {
    	Intent intent = new Intent(this, AddToiletActivity.class);
    	startActivity(intent);
    }
    
    // TODO borrar ese metodo
    public void onJumpMapTapped(View view)
    {
    	Intent intent = new Intent(this, DetailToiletActivity.class);
		intent.putExtra(MappingToiletActivity.EXTRA_TOILET_ID, UUID.randomUUID().toString());
    	startActivity(intent);
    }
    
}
