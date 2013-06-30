package com.example.appsample;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class FullscreenActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.fullscreen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
	    switch (item.getItemId())
	    {
	    case R.id.action_settings:
	        startActivity(new Intent(this, SettingsActivity.class));
	        return true;
	    default:
	        return super.onOptionsItemSelected(item);
	    }
    }
    
    public void findNearToiletsTapped(View view)
    {
    	Intent intent = new Intent(this, DisplayNearToiletsActivity.class);
    	startActivity(intent);
    }
    
    public void settingsMenuItemTapped(View view)
    {
    	Intent intent = new Intent(this, SettingsActivity.class);
    	startActivity(intent);
    }
}
