package socialtoilet.android.activities;

import socialtoilet.android.R;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.SeekBar;
import android.widget.ToggleButton;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.app.Activity;
import socialtoilet.android.utils.Settings;

public class SettingsActivity extends Activity
{
	protected static final int DEFAULT_OFFSET_METTERS = 100;
	protected static final String MEASUREMENT_UNIT = " mts";
	
	private SeekBar bar; 
	private TextView textProgress;
	private ToggleButton debugButton;
	private int currentDistance=200;
	private int seekBarPosition;
	private Settings settings;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		
		settings = Settings.getInstance();
		currentDistance = settings.getInitialRadiusInMeters();
		seekBarPosition = currentDistance - DEFAULT_OFFSET_METTERS;
		
		bar = (SeekBar)findViewById(R.id.map_search_distance_seek_bar); 
		bar.setProgress(seekBarPosition);
	    
		textProgress = (TextView)findViewById(R.id.map_search_distance_value);
	    textProgress.setText(Integer.toString(currentDistance)+MEASUREMENT_UNIT);
	    
		bar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {		
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {		
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				currentDistance=progress+DEFAULT_OFFSET_METTERS;
				settings.setDistanceForNearToiletsInMeters(currentDistance);
				textProgress.setText(Integer.toString(currentDistance)+MEASUREMENT_UNIT);
				
			}
		});
		 
	    debugButton  = (ToggleButton)findViewById(R.id.toggleButton1);
	    debugButton.setChecked(settings.isServicesDebugMode());
	    
	    debugButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (debugButton.isChecked()) {
					settings.setServicesDebugMode(true);
				}
				else {
					settings.setServicesDebugMode(false);
				}
			}
		});
	    
	}
}
