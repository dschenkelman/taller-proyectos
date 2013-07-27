package socialtoilet.android.activities;

import java.util.Collection;
import java.util.UUID;

import socialtoilet.android.R;
import socialtoilet.android.model.IComment;
import socialtoilet.android.services.IRetrieveToiletCommentsService;
import socialtoilet.android.services.IRetrieveToiletCommentsServiceDelegate;
import socialtoilet.android.services.factories.ServicesFactory;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;

public class ToiletCommentsActivity extends Activity 
	implements IRetrieveToiletCommentsServiceDelegate
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setupActionBar();
		setContentView(R.layout.activity_toilet_comments);
		
		Intent intent = getIntent();
		String toiletId = intent.getStringExtra(DetailToiletActivity.EXTRA_TOILET_ID);
		UUID id = UUID.fromString(toiletId);
		
		IRetrieveToiletCommentsService service = ServicesFactory.createRetrieveToiletCommentsService();
		service.retrieveToiletComments(id, this);
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
		getMenuInflater().inflate(R.menu.toilet_comments, menu);
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
	public void retrieveToiletCommentsFinish(
			IRetrieveToiletCommentsService service, Collection<IComment> comments)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void retrieveToiletCommentsFinishWithError(
			IRetrieveToiletCommentsService service, String errorCode)
	{
		// TODO Auto-generated method stub
		
	}

}
