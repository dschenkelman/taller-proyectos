package socialtoilet.android.activities;

import java.util.UUID;

import socialtoilet.android.R;
import socialtoilet.android.activities.dialogs.CalificationDialogFragment;
import socialtoilet.android.activities.dialogs.CalificationDialogFragment.ICalificationDialogDataSource;
import socialtoilet.android.activities.dialogs.CalificationDialogFragment.ICalificationDialogDelegate;
import socialtoilet.android.model.IRating;
import socialtoilet.android.model.IToilet;
import socialtoilet.android.services.IQualificateToiletService;
import socialtoilet.android.services.IQualificateToiletServiceDelegate;
import socialtoilet.android.services.IRetrieveToiletRatingService;
import socialtoilet.android.services.IRetrieveToiletRatingServiceDelegate;
import socialtoilet.android.services.IRetrieveToiletUserQualificationService;
import socialtoilet.android.services.IRetrieveToiletUserQualificationServiceDelegate;
import socialtoilet.android.services.factories.ServicesFactory;
import socialtoilet.android.utils.StateSaver;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;

public class DetailToiletActivity extends FragmentActivity
	implements ICalificationDialogDataSource,
	ICalificationDialogDelegate, IQualificateToiletServiceDelegate,
	IRetrieveToiletRatingServiceDelegate, IRetrieveToiletUserQualificationServiceDelegate
{

	public final static String KEY_UUID_OBJECT_RETRIEVER = "kToiletStream";
	public static final String EXTRA_TOILET_ID = "socialtoilet.andorid.detailtoilet.TOILET";
	private IToilet toilet;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail_toilet);
		this.disableClickableCheckboxs();
		setupActionBar();
		
	    if(null == savedInstanceState)
	    {
			toilet = StateSaver.getInstance().retrieveToilet(MappingToiletActivity.EXTRA_TOILET_ID);
			if(null != toilet)
			{
				populateData();
				
				IRetrieveToiletRatingService service = ServicesFactory.createRetrieveToiletRatingService();
				service.retrieveToiletRating(toilet.getID().toString(), this);
				IRetrieveToiletUserQualificationService calificationService = 
						ServicesFactory.createRetrieveToiletUserCalificationService();
				calificationService.retrieveToiletUserCalification(this, toilet.getID().toString());
			}
			else
			{
				// TODO onToiletNull
			}
	    }
	    else
	    {
		    String objectUUID = savedInstanceState.getString(KEY_UUID_OBJECT_RETRIEVER);
		    UUID objectToRetrieveUUID = UUID.fromString(objectUUID);
		    toilet = StateSaver.getInstance().retrieveToilet(objectToRetrieveUUID.toString());
			this.populateData();
	    }
	    
	    final RatingBar ratingBar = (RatingBar) findViewById(R.id.userCalification);
	    ratingBar.setIsIndicator(true);
	    ratingBar.setOnTouchListener(new OnTouchListener()
	    {
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1)
			{
				onCalificationButtonTapped(ratingBar);
				return false;
			}
	    });
	}
	
	private void disableClickableCheckboxs(){
		LinearLayout myLayout = (LinearLayout) findViewById(R.id.checkboxLayout);
		LinearLayout view = (LinearLayout) myLayout.getChildAt(0);
		for ( int i = 0; i < view.getChildCount();  i++ ){
		    View checkbox = view.getChildAt(i);
		    checkbox.setClickable(false);
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
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.detail_toilet, menu);
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
		case R.id.item_comment_button:
			onCommentsButtonTapped();
	    	return true;
		case R.id.item_gallery_button:
			onGaleryButtonTapped();
	    	return true;
		case R.id.action_settings:
	        startActivity(new Intent(this, SettingsActivity.class));
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
	
	private void populateData()
	{
		TextView title = (TextView) findViewById(R.id.toiletDescription);
		title.setText(toilet.getDescription());

		TextView address = (TextView) findViewById(R.id.toiletAddress);
		address.setText(toilet.getAddress());
		
		CheckBox cb;
		cb = (CheckBox) findViewById(R.id.canBeUsedWithoutConsumption);
		cb.setChecked(toilet.canBeUsedWithoutConsumption());
		cb = (CheckBox) findViewById(R.id.hasWater);
		cb.setChecked(toilet.hasWater());
		cb = (CheckBox) findViewById(R.id.hasToiletPaper);
		cb.setChecked(toilet.hasToiletPaper());
		cb = (CheckBox) findViewById(R.id.hasSoap);
		cb.setChecked(toilet.hasSoap());
		cb = (CheckBox) findViewById(R.id.hasMirror);
		cb.setChecked(toilet.hasMirror());
		cb = (CheckBox) findViewById(R.id.doToiletDoorCloses);
		cb.setChecked(toilet.doToiletDoorCloses());
		cb = (CheckBox) findViewById(R.id.hasGotLadiesItemsOnSale);
		cb.setChecked(toilet.hasGotLadiesItemsOnSale());
		cb = (CheckBox) findViewById(R.id.hasGotCondomsOnSale);
		cb.setChecked(toilet.hasGotCondomsOnSale());
		cb = (CheckBox) findViewById(R.id.isAptForHandicapped);
		cb.setChecked(toilet.isAptForHandicapped());
		cb = (CheckBox) findViewById(R.id.hasBabyRoom);
		cb.setChecked(toilet.hasBabyRoom());
	}

	private void populateGlobalRating()
	{
		RatingBar ratingBar = (RatingBar) findViewById(R.id.globalRating);
		ratingBar.setRating(toilet.getRanking());
		TextView calificationsCount = (TextView) findViewById(R.id.calificationCount);
		calificationsCount.setText(toilet.getUserCalificationsCount() + "");
	}
	
	private void populateUserCalification()
	{
		RatingBar userCalificationBar = (RatingBar) findViewById(R.id.userCalification);
		userCalificationBar.setRating(toilet.getUserCalification());
	}
	
    public void onCalificationButtonTapped(View view)
    {
    	CalificationDialogFragment dialog = new CalificationDialogFragment();
    	dialog.show(getSupportFragmentManager(), "calificate");
    }
	
    private void onCloseButtonTapped()
    {
     	Intent intent = new Intent(this, StartSessionActivity.class);
     	intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);	
	}
    
    public void onCommentsButtonTapped()
    {
    	Intent intent = new Intent(this, ToiletCommentsActivity.class);
		intent.putExtra(DetailToiletActivity.EXTRA_TOILET_ID, toilet.getID().toString());
    	startActivity(intent);
    }

    public void onGaleryButtonTapped()
    {
    	Intent intent = new Intent(this, ToiletGaleryActivity.class);
		intent.putExtra(DetailToiletActivity.EXTRA_TOILET_ID, toilet.getID().toString());
    	startActivity(intent);
    }
    
	@Override
	public void onDialogCalificateClick(CalificationDialogFragment dialog)
	{
		int userCalification = dialog.getUserCalification();
		toilet.setUserCalification(userCalification);
		if(1 != toilet.getUserCalificationsCount())
		{
			populateGlobalRating();
		}
		populateUserCalification();
		IQualificateToiletService calificate = ServicesFactory.createCalificateToiletService();
		calificate.qualificateToiletService(toilet, userCalification, this);
	}

	@Override
	public void onDialogCancelClick(CalificationDialogFragment dialog) { }

	@Override
	public float getUserCalification()
	{
		return toilet.getUserCalification();
	}

	@Override
	public void calificateToiletFinish(IQualificateToiletService service) { }

	@Override
	public void calificateToiletFinishWithError(
			IQualificateToiletService service, int errorCode)
	{
		toilet.revertUserCalification();
		populateGlobalRating();
		populateUserCalification();
	}

	@Override
	public void retrieveToiletRatingServiceFinish(
			IRetrieveToiletRatingService service, IRating rating)
	{
		toilet.setRating(rating);
		populateGlobalRating();
	}

	@Override
	public void retrieveToiletRatingServiceFinishWithError(
			IRetrieveToiletRatingService service, String errorCode)
	{
	}

	@Override
	public void retrieveToiletUserQualificationServiceFinish(
			IRetrieveToiletUserQualificationService service, double calification)
	{
		toilet.setUserCalification((int)calification);
		populateGlobalRating();
		populateUserCalification();
	}


	@Override
	public void retrieveToiletUserQualificationServiceFinishWithError(
			IRetrieveToiletUserQualificationService service, int errorCode)
	{
	}
}
