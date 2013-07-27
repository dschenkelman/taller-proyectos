package socialtoilet.android.activities;

import java.util.UUID;

import socialtoilet.android.R;
import socialtoilet.android.activities.dialogs.CalificationDialogFragment;
import socialtoilet.android.activities.dialogs.CalificationDialogFragment.ICalificationDialogDataSource;
import socialtoilet.android.activities.dialogs.CalificationDialogFragment.ICalificationDialogDelegate;
import socialtoilet.android.model.IToilet;
import socialtoilet.android.services.ICalificateToiletService;
import socialtoilet.android.services.ICalificateToiletServiceDelegate;
import socialtoilet.android.services.IRetrieveToiletService;
import socialtoilet.android.services.IRetrieveToiletServiceDelegate;
import socialtoilet.android.services.factories.ServicesFactory;
import socialtoilet.android.utils.StateSaver;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;

public class DetailToiletActivity extends FragmentActivity
	implements IRetrieveToiletServiceDelegate, ICalificationDialogDataSource, ICalificationDialogDelegate, ICalificateToiletServiceDelegate
{

	public final static String KEY_UUID_OBJECT_RETRIEVER = "kToiletStream";
	private IToilet toilet;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		Configuration config = getResources().getConfiguration();
	    if (config.orientation == Configuration.ORIENTATION_LANDSCAPE)
	    {
			Log.d("Social Toilet", "ORIENTATION_LANDSCAPE");
			setContentView(R.layout.activity_detail_toilet_landscape);
	    }
	    else if (config.orientation == Configuration.ORIENTATION_PORTRAIT)
	    {
			Log.d("Social Toilet", "ORIENTATION_PORTRAIT");
			setContentView(R.layout.activity_detail_toilet);
	    }
	    
	    if(null == savedInstanceState)
	    {
			Log.d("Social Toilet", "Primera vez que entro a la view");
			setupActionBar();
			
			Intent intent = getIntent();
			String toiletId = intent.getStringExtra(MappingToiletActivity.EXTRA_TOILET_ID);
			
			UUID id = UUID.fromString(toiletId);
			
			
			IRetrieveToiletService service = ServicesFactory.createRetrieveToiletService();//new RetrieveToiletService();
			service.retrieveToilet(id, this);
	    }
	    else
	    {
			Log.d("Social Toilet", "No es la primera vez, vengo de una rotación");
		    String objectUUID = savedInstanceState.getString(KEY_UUID_OBJECT_RETRIEVER);
		    UUID objectToRetrieveUUID = UUID.fromString(objectUUID);
		    toilet = StateSaver.getInstance().retrieveToilet(objectToRetrieveUUID.toString());
			this.populateData();
	    }
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
	protected void onSaveInstanceState(Bundle icicle)
	{
		super.onSaveInstanceState(icicle);
		UUID id = UUID.randomUUID();
		StateSaver.getInstance().saveToilet(id.toString(), toilet);
		icicle.putString(KEY_UUID_OBJECT_RETRIEVER, id.toString());
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
		ratingBar.setRating(toilet.getRanking());

		TextView calificationsCount = (TextView) findViewById(R.id.calificationCount);
		calificationsCount.setText(toilet.getUserCalificationsCount() + " calificaciones");
	}
	
    public void onCalificationButtonTapped(View view)
    {
    	CalificationDialogFragment dialog = new CalificationDialogFragment();
    	dialog.show(getSupportFragmentManager(), "calificate");
    }

	@Override
	public void onDialogCalificateClick(CalificationDialogFragment dialog)
	{
		int userCalification = dialog.getUserCalification();
		
		toilet.setUserCalification(userCalification);
		
		RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar1);
		ratingBar.setRating(toilet.getRanking());
		
		TextView calificationsCount = (TextView) findViewById(R.id.calificationCount);
		calificationsCount.setText(toilet.getUserCalificationsCount() + " calificaciones");
		
		ICalificateToiletService calificate = ServicesFactory.createCalificateToiletService();
		calificate.calificateToiletService(toilet, userCalification, this);
	}

	@Override
	public void onDialogCancelClick(CalificationDialogFragment dialog) { }

	@Override
	public float getUserCalification()
	{
		return toilet.getUserCalification();
	}

	@Override
	public void calificateToiletFinish(ICalificateToiletService service) { }

	@Override
	public void calificateToiletFinishWithError(
			ICalificateToiletService service, String errorCode)
	{
		// TODO go calification back
		toilet.setUserCalification(0);
	}
}
