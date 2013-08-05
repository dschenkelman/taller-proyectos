package socialtoilet.android.activities;

import java.util.Collection;

import socialtoilet.android.R;
import socialtoilet.android.activities.dialogs.AddCommentDialogFragment.IAddCommentDialogDelegate;
import socialtoilet.android.model.IToilet;
import socialtoilet.android.model.IToiletCreatedDelegate;
import socialtoilet.android.model.IToiletTrait;
import socialtoilet.android.model.Toilet;
import socialtoilet.android.services.factories.ServicesFactory;
import socialtoilet.android.services.get.IRetrieveTraitsService;
import socialtoilet.android.services.get.IRetrieveTraitsServiceDelegate;
import socialtoilet.android.services.post.IAddToiletService;
import socialtoilet.android.services.post.IAddToiletServiceDelegate;
import socialtoilet.android.services.post.IQualificateToiletService;
import socialtoilet.android.services.post.IQualificateToiletServiceDelegate;
import socialtoilet.android.services.put.IEditToiletTraitsService;
import socialtoilet.android.services.put.IEditToiletTraitsServiceDelegate;
import socialtoilet.android.utils.Settings;
import socialtoilet.android.utils.StateSaver;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;

public class AddToiletActivity extends Activity implements 
	IAddToiletServiceDelegate, IRetrieveTraitsServiceDelegate, 
	IQualificateToiletServiceDelegate, IEditToiletTraitsServiceDelegate
{

	private IToiletCreatedDelegate delegate;
	private Collection<IToiletTrait> traits;
	private double latitude;
	private double longitude;
	private boolean shouldWaitForEditToiletTraitsService;
	private boolean shouldWaitForQualificateService;
	private Toilet toAddToilet;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_toilet);
		setupActionBar();
		
		Intent intent = getIntent();
		latitude = intent.getDoubleExtra(MappingToiletActivity.EXTRA_LATITUDE, -34.6208718);
		longitude = intent.getDoubleExtra(MappingToiletActivity.EXTRA_LONGITUDE, -58.4318558);

		delegate = (IToiletCreatedDelegate) StateSaver.getInstance().retrieve(MappingToiletActivity.TOILET_CREATED_DELEGATE);
		
		IRetrieveTraitsService service = 
				ServicesFactory.createRetrieveTraitsService();
		service.retrieveTraits(this);
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
    
	private Toilet generateToilet()
	{

    	EditText description = (EditText)this.findViewById(R.id.editDescription);
    	EditText address = (EditText)this.findViewById(R.id.editAddress);
    	
		Toilet toilet = new Toilet();
		toilet.setDescription(description.getText().toString());
		toilet.setAddress(address.getText().toString());
		toilet.setLocation(latitude, longitude);
		
		return toilet;
	}

	public void onAddToilet(View view)
	{
		toAddToilet = generateToilet();
		if(null != toAddToilet)
		{
			shouldWaitForEditToiletTraitsService = false;
			shouldWaitForQualificateService = false;
			
			IAddToiletService service = ServicesFactory.createAddToiletService();
			service.addToilet(toAddToilet, this);
		}
	}
	
    public void onCleanData(View view)
    {
    	((EditText)this.findViewById(R.id.editDescription)).setText("");
    	((EditText)this.findViewById(R.id.editAddress)).setText("");
    	((RatingBar)this.findViewById(R.id.toiletRatingBar)).setRating(0);
    	
    	for(IToiletTrait trait : traits)
    	{
    		trait.setHasDescription(false);
    	}
    	populateTraits();
    }

	private void populateTraits()
	{
		LinearLayout container = (LinearLayout) findViewById(R.id.checkboxLayout);
		container.removeAllViews();
		for(final IToiletTrait trait : traits)
		{
			final CheckBox cb = new CheckBox(this);
			cb.setId(trait.getId());
			cb.setText(trait.getDescription());
			cb.setTextColor(Color.WHITE);
			cb.setButtonDrawable(getResources().getDrawable(R.drawable.st_checkbox));
			cb.setChecked(trait.hasDescription());
			cb.setOnCheckedChangeListener(new OnCheckedChangeListener()
			{
				@Override
				public void onCheckedChanged(CompoundButton arg0, boolean arg1)
				{
					trait.setHasDescription(cb.isChecked());
				}
			});
			container.addView(cb);
		}
	}

	private void returnToMap()
	{
		if(null != delegate)
		{
			delegate.toiletCreated(toAddToilet);
		}
		NavUtils.navigateUpFromSameTask(this);
	}
	
	@Override
	public void retrieveTraitsServiceFinish(
			IRetrieveTraitsService service, Collection<IToiletTrait> traits)
	{
		this.traits = traits;

		TextView loadingTraitsView = (TextView) findViewById(R.id.loadingTraits);
		loadingTraitsView.setVisibility(TextView.GONE);
		
		populateTraits();
	}

	@Override
	public void retrieveTraitsServiceFinishWithError(
			IRetrieveTraitsService service, int errorCode)
	{
		// TODO retry
	}

	@Override
	public void addToiletFinish(IAddToiletService service, IToilet toilet)
	{
		RatingBar qualificationBar = (RatingBar)this.findViewById(R.id.toiletRatingBar);
		if(0 != (int)qualificationBar.getRating())
		{
			IQualificateToiletService qservice = ServicesFactory.createQualificateToiletService();
			qservice.qualificateToiletService(toilet, (int)qualificationBar.getRating(), this);
			shouldWaitForQualificateService = true;
		}
		
		IEditToiletTraitsService etService = ServicesFactory.createEditToiletTraitsService();
		etService.editToiletTraits(this, traits, toilet.getID().toString());
		Log.d("Social Toilet", "addToiletFinish");
		shouldWaitForEditToiletTraitsService = true;
	}

	@Override
	public void addToiletFinishWithError(IAddToiletService service,
			String errorCode)
	{
		// TODO show error
		Log.d("Social Toilet", "addToiletFinishWithError");
	}

	@Override
	public void qualificateToiletFinish(IQualificateToiletService service)
	{
		Log.d("Social Toilet", "qualificateToiletFinish");
		shouldWaitForQualificateService = false;
		if(false == shouldWaitForEditToiletTraitsService)
		{
			returnToMap();
		}
	}

	@Override
	public void qualificateToiletFinishWithError(
			IQualificateToiletService service, int errorCode)
	{
		// TODO retry
		Log.d("Social Toilet", "qualificateToiletFinishWithError");
		shouldWaitForQualificateService = false;
		if(false == shouldWaitForEditToiletTraitsService)
		{
			returnToMap();
		}
	}

	@Override
	public void editToiletTraitsServiceFinish(IEditToiletTraitsService service)
	{
		Log.d("Social Toilet", "editToiletTraitsServiceFinish");
		shouldWaitForEditToiletTraitsService = false;
		if(false == shouldWaitForQualificateService)
		{
			returnToMap();
		}
	}

	@Override
	public void editToiletTraitsServiceFinishWithError(
			IEditToiletTraitsService service, int errorCode)
	{
		// TODO retry
		Log.d("Social Toilet", "editToiletTraitsServiceFinishWithError");
		shouldWaitForEditToiletTraitsService = false;
		if(false == shouldWaitForQualificateService)
		{
			returnToMap();
		}
	}

}
