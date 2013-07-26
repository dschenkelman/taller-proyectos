package socialtoilet.android;

import java.util.UUID;

import socialtoilet.android.model.IToilet;
import socialtoilet.android.services.IRetrieveToiletService;
import socialtoilet.android.services.IRetrieveToiletServiceDelegate;
import socialtoilet.android.services.factories.ServicesFactory;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RatingBar;
import android.widget.TextView;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;

public class DetailToiletActivity extends Activity implements IRetrieveToiletServiceDelegate {

	private IToilet toilet;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Configuration config = getResources().getConfiguration();
	    if (config.orientation == Configuration.ORIENTATION_LANDSCAPE)
	    {
			Log.d("Social Toilet", "ORIENTATION_LANDSCAPE");
			setContentView(R.layout.activity_detail_toilet_landscape);
	    }
	    else if (config.orientation == Configuration.ORIENTATION_PORTRAIT)
	    {
			Log.d("Social Toilet", "ORIENTATION_PORTRAIT\n");
			setContentView(R.layout.activity_detail_toilet);
	    }
	    
	    
	    
		// Show the Up button in the action bar.
		setupActionBar();
		
		Intent intent = getIntent();
		String toiletId = intent.getStringExtra(MappingToiletActivity.EXTRA_TOILET_ID);
		
		UUID id = UUID.fromString(toiletId);
		
		
		IRetrieveToiletService service = ServicesFactory.createRetrieveToiletService();//new RetrieveToiletService();
		service.retrieveToilet(id, this);
		

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
		getMenuInflater().inflate(R.menu.detail_toilet, menu);
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
	
	@Override
	public void retrieveToiletFinish(IRetrieveToiletService mockRetrieveToiletsService, IToilet toilet)
	{
		this.toilet = toilet;
		this.populateData();
	}

	@Override
	public void retrieveToiletFinishWithError(IRetrieveToiletService mockRetrieveToiletsService, String errorCode) {}
	
	private void populateData(){
		TextView title = (TextView) findViewById(R.id.toiletDescription);
		title.setText(toilet.getDescription());

		TextView address = (TextView) findViewById(R.id.toiletAddress);
		address.setText(toilet.getAddress());
		
		RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar1);
		ratingBar.setNumStars(toilet.getRanking());
	}

}
