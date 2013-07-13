package socialtoilet.android;

import socialtoilet.android.location.GPSTracker;
import socialtoilet.android.model.Toilet;
import socialtoilet.android.services.AddToiletService;
import socialtoilet.android.services.IAddToiletService;
import socialtoilet.android.services.IAddToiletServiceDelegate;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.os.Build;

public class AddToiletActivity extends Activity implements IAddToiletServiceDelegate
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_toilet);
		// Show the Up button in the action bar.
		setupActionBar();
		
		TextView location = (TextView)findViewById(R.id.textView1);
		location.setText(GPSTracker.getInstance().getLatitude() + " " + GPSTracker.getInstance().getLongitude());
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
		getMenuInflater().inflate(R.menu.add_toilet, menu);
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

	public void addToiletTapped(View view)
	{
		Toilet toilet = generateToilet();
		
		IAddToiletService service = new AddToiletService();
		service.addToilet(toilet, this);
	}

	private Toilet generateToilet()
	{
		// TODO 
		Toilet toilet = new Toilet();
		toilet.setDescription("Damian");
		toilet.setAddress("Lo de dami");
		toilet.setLocation(-34.6208718, -58.4318558);
		
		return toilet;
	}

	@Override
	public void addToiletFinish(IAddToiletService service)
	{
		Toast.makeText(getApplicationContext(), "Toilet added", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void addToiletFinishWithError(IAddToiletService service,
			String errorCode)
	{
		Toast.makeText(getApplicationContext(), "Error adding toilet", Toast.LENGTH_SHORT).show();
		
	}
}
