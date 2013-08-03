package socialtoilet.android.activities;

import java.util.Collection;
import java.util.UUID;

import socialtoilet.android.R;
import socialtoilet.android.model.GaleryManager;
import socialtoilet.android.model.IToiletPicture;
import socialtoilet.android.services.IRetrieveToiletGaleryService;
import socialtoilet.android.services.IRetrieveToiletGaleryServiceDelegate;
import socialtoilet.android.services.factories.ServicesFactory;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;

public class ToiletGaleryActivity extends Activity
	implements IRetrieveToiletGaleryServiceDelegate
{
	private GaleryManager manager; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_toilet_galery);
		setupActionBar();
		
		Intent intent = getIntent();
		String toiletId = intent.getStringExtra(DetailToiletActivity.EXTRA_TOILET_ID);
		UUID id = UUID.fromString(toiletId);
		
		manager = new GaleryManager();
		
		IRetrieveToiletGaleryService service = ServicesFactory.createRetrieveToiletGaleryService();
		service.retrieveToiletGalery(id, this);
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
		getMenuInflater().inflate(R.menu.toilet_galery, menu);
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
		case R.id.action_settings:
	        startActivity(new Intent(this, SettingsActivity.class));
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void retrieveToiletGaleryServiceFinish(
			IRetrieveToiletGaleryService service,
			Collection<IToiletPicture> comments) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void retrieveToiletGaleryServiceFinishWithError(
			IRetrieveToiletGaleryService service, String errorCode) {
		// TODO Auto-generated method stub
		
	}
}
