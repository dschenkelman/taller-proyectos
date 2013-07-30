package socialtoilet.android.activities;

import java.util.UUID;

import com.google.ads.AdView;

import socialtoilet.android.R;
import socialtoilet.android.model.Toilet;
import socialtoilet.android.services.IAddToiletService;
import socialtoilet.android.services.IAddToiletServiceDelegate;
import socialtoilet.android.services.factories.ServicesFactory;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;

public class AddToiletActivity extends Activity implements IAddToiletServiceDelegate
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_toilet);
		setupActionBar();
	    
	    if(null == savedInstanceState)
	    {
	    	// TODO initialize with the default data
	    }
	    else
	    {
	    	// TODO retrieve the data before the orientation change
	    }
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
		}
		return super.onOptionsItemSelected(item);
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
}
