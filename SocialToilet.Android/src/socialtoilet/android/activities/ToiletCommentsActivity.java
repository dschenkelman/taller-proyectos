package socialtoilet.android.activities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import socialtoilet.android.R;
import socialtoilet.android.model.IComment;
import socialtoilet.android.services.IRetrieveToiletCommentsService;
import socialtoilet.android.services.IRetrieveToiletCommentsServiceDelegate;
import socialtoilet.android.services.factories.ServicesFactory;
import android.os.Bundle;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;

public class ToiletCommentsActivity extends Activity 
	implements IRetrieveToiletCommentsServiceDelegate
{

	private Collection<IComment> comments;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setupActionBar();
		setContentView(R.layout.activity_toilet_comments);
		
		Intent intent = getIntent();
		String toiletId = intent.getStringExtra(DetailToiletActivity.EXTRA_TOILET_ID);
		UUID id = UUID.fromString(toiletId);

		comments = new ArrayList<IComment>();
		
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

	private void populateCommentsView()
	{
	    LayoutInflater inflater = getLayoutInflater();
	    View commentView;
	    TextView commentTitle;
	    TextView commentMessage;
	    TextView user;
	    TextView commentDate;
		LinearLayout linearLayout = (LinearLayout)findViewById(R.id.toiletCommentsCommentsLinearLayout);
		linearLayout.removeAllViews();
		
		int textColor = Color.argb(255, 255, 255, 255);
		
		for(IComment comment : comments)
		{
			commentView = inflater.inflate(R.layout.toilet_comment_row, null);
	        commentTitle = (TextView) commentView.findViewById(R.id.comment_title);
	        commentMessage = (TextView) commentView.findViewById(R.id.comment_message);
	        user = (TextView) commentView.findViewById(R.id.comment_user);
	        commentDate = (TextView) commentView.findViewById(R.id.comment_date);
	        commentTitle.setText(comment.getTitle());
	        commentMessage.setText(comment.getMessage());
	        user.setText(comment.getUser());
	        commentDate.setText(comment.getDate());
	        
	        commentTitle.setTextColor(textColor);
	        commentMessage.setTextColor(textColor);
	        user.setTextColor(textColor);
	        commentDate.setTextColor(textColor);
	        
	        linearLayout.addView(commentView);
		}
	}

	@Override
	public void retrieveToiletCommentsFinish(
			IRetrieveToiletCommentsService service, Collection<IComment> comments)
	{
		this.comments.clear();
		this.comments.addAll(comments);
		populateCommentsView();
	}

	@Override
	public void retrieveToiletCommentsFinishWithError(
			IRetrieveToiletCommentsService service, String errorCode)
	{
		// TODO Auto-generated method stub
		
	}

}
