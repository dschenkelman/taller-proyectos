package socialtoilet.android.activities;

import socialtoilet.android.R;
import android.os.Bundle;
import android.widget.SeekBar;
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
	private int currentDistance=200;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		
		 bar = (SeekBar)findViewById(R.id.map_search_distance_seek_bar); // make seekbar object
	    //bar.setOnSeekBarChangeListener(this);
		 bar.setProgress(INITIAL_PROGRESS_VALUE);
		 bar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				currentDistance=progress+DEFAULT_OFFSET_METTERS;
				textProgress.setText(currentDistance+ MEASUREMENT_UNIT);
				
			}
		});
		 
	    textProgress = (TextView)findViewById(R.id.map_search_distance_value);
	}
}
