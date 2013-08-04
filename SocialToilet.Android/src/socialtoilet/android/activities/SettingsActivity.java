package socialtoilet.android.activities;

import java.util.TooManyListenersException;

import socialtoilet.android.R;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.SeekBar;
import android.widget.ToggleButton;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.app.Activity;

public class SettingsActivity extends Activity
{
	protected static final int DEFAULT_OFFSET_METTERS = 100;
	protected static final int INITIAL_PROGRESS_VALUE = 100;
	protected static final String MEASUREMENT_UNIT = " mts";
	
	private SeekBar bar; 
	private TextView textProgress;
	private ToggleButton debugButton;
	private int currentDistance=200;
	private boolean debugMode = false;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		
		bar = (SeekBar)findViewById(R.id.map_search_distance_seek_bar); 
		bar.setProgress(INITIAL_PROGRESS_VALUE);
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
				textProgress.setText(currentDistance+ MEASUREMENT_UNIT);
				
			}
		});
		 
	    textProgress = (TextView)findViewById(R.id.map_search_distance_value);
	    debugButton  = (ToggleButton)findViewById(R.id.toggleButton1);
	    debugButton.setSelected(debugMode);
	    debugButton.setOnClickListener(new OnClickListener() {
	    	@Override
	        public void onClick(View v) {
	        	if((debugButton.isChecked())) {
	                  debugMode = true;
	            }
	            else {
	            	  debugMode = false;
	            }
	        }
	    });
	}
}
