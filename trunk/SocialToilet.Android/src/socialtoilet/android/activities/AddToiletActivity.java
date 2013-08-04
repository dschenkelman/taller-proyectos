package socialtoilet.android.activities;

import java.util.Collection;

import socialtoilet.android.R;
import socialtoilet.android.model.IToiletTrait;
import socialtoilet.android.model.Toilet;
import socialtoilet.android.services.factories.ServicesFactory;
import socialtoilet.android.services.get.IRetrieveToiletTraitsService;
import socialtoilet.android.services.get.IRetrieveToiletTraitsServiceDelegate;
import socialtoilet.android.services.post.IAddToiletService;
import socialtoilet.android.services.post.IAddToiletServiceDelegate;
import socialtoilet.android.utils.Settings;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;

public class AddToiletActivity extends Activity implements 
	IAddToiletServiceDelegate, IRetrieveToiletTraitsServiceDelegate
{

	private Collection<IToiletTrait> traits;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_toilet);
		setupActionBar();
		
		IRetrieveToiletTraitsService service = 
				ServicesFactory.createRetrieveToiletTraitsService();
		service.retrieveToiletTraits(this);
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
			NavUtils.navigateUpFromSameTask(this);
			return true;
		case R.id.action_exit:
			onCloseButtonTapped();
	    	return true;
		case R.id.action_settings:
	        startActivity(new Intent(this, SettingsActivity.class));
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
    private void onCloseButtonTapped()
    {
    	Settings.getInstance().logoutUser();
     	Intent intent = new Intent(this, StartSessionActivity.class);
     	intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);	
	}
    
	public void addToiletTapped(View view)
	{
		Toilet toilet = generateToilet();
		
		IAddToiletService service = ServicesFactory.createAddToiletService();//new AddToiletService();
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
	
    public void onCleanData(View view)
    {
    	((EditText)this.findViewById(R.id.editDescription)).setText("");
    	((EditText)this.findViewById(R.id.editAddress)).setText("");
    	((RatingBar)this.findViewById(R.id.toiletRatingBar)).setRating(0);
    	
    	RelativeLayout myLayout = (RelativeLayout) findViewById(R.id.checkboxLayout);
		LinearLayout checkboxLayout = (LinearLayout) myLayout.getChildAt(0);
		for ( int i = 0; i < checkboxLayout.getChildCount();  i++ ){
			CheckBox checkbox = (CheckBox)checkboxLayout.getChildAt(i);
		    checkbox.setChecked(false);
		}
    }

	@Override
	public void retrieveToiletTraitsServiceFinish(
			IRetrieveToiletTraitsService service,
			Collection<IToiletTrait> traits)
	{
		this.traits = traits;

		TextView loadingTraitsView = (TextView) findViewById(R.id.loadingTraits);
		loadingTraitsView.setVisibility(TextView.GONE);
		
		populateTraits();
	}

	private void populateTraits()
	{
		LinearLayout container = (LinearLayout) findViewById(R.id.checkboxLayout);
		for(IToiletTrait trait : traits)
		{
			CheckBox cb = new CheckBox(this);
			cb.setId(trait.getId());
			cb.setText(trait.getDescription());
			cb.setTextColor(Color.WHITE);
			
			cb.setButtonDrawable(getResources().getDrawable(R.drawable.st_checkbox));
			
			container.addView(cb);
		}
	}

	@Override
	public void retrieveToiletTraitsServiceFinishWithError(
			IRetrieveToiletTraitsService service, int errorCode)
	{
		// TODO
		TextView loadingTraitsView = (TextView) findViewById(R.id.loadingTraits);
		loadingTraitsView.setText("Reintentar");
	}
}
