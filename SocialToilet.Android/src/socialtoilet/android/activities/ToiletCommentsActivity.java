package socialtoilet.android.activities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import socialtoilet.android.R;
import socialtoilet.android.activities.dialogs.AddCommentDialogFragment;
import socialtoilet.android.activities.dialogs.AddCommentDialogFragment.IAddCommentDialogDelegate;
import socialtoilet.android.model.Comment;
import socialtoilet.android.model.IComment;
import socialtoilet.android.services.IAddToiletCommentService;
import socialtoilet.android.services.IAddToiletCommentServiceDelegate;
import socialtoilet.android.services.IRetrieveToiletCommentsService;
import socialtoilet.android.services.IRetrieveToiletCommentsServiceDelegate;
import socialtoilet.android.services.factories.ServicesFactory;
import socialtoilet.android.utils.Settings;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;

public class ToiletCommentsActivity extends FragmentActivity 
	implements IRetrieveToiletCommentsServiceDelegate,
		IAddCommentDialogDelegate, IAddToiletCommentServiceDelegate
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
			NavUtils.navigateUpFromSameTask(this);
			return true;
		case R.id.add_comment_button:
			onAddCommentButtonTapped();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void populateCommentsView()
	{
		LinearLayout linearLayout = (LinearLayout)findViewById(R.id.toiletCommentsCommentsLinearLayout);
		linearLayout.removeAllViews();
		for(IComment comment : comments)
		{
			addCommentToView(comment);
		}
	}

	private void appendComment(IComment comment)
	{
		comments.add(comment);
		addCommentToView(comment);
	}

	private void addCommentToView(final IComment comment)
	{
	    LayoutInflater inflater = getLayoutInflater();
	    View commentView;
	    TextView commentTitle;
	    TextView commentMessage;
	    TextView user;
	    TextView commentDate;
	    ImageButton eraseComment;
		LinearLayout linearLayout = (LinearLayout)findViewById(R.id.toiletCommentsCommentsLinearLayout);
		
		commentView = inflater.inflate(R.layout.toilet_comment_row, null);
        commentTitle = (TextView) commentView.findViewById(R.id.comment_title);
        commentMessage = (TextView) commentView.findViewById(R.id.comment_message);
        user = (TextView) commentView.findViewById(R.id.comment_user);
        commentDate = (TextView) commentView.findViewById(R.id.comment_date);
        eraseComment = (ImageButton) commentView.findViewById(R.id.comment_erase);
        
        if(0 != comment.getTitle().length())
        {
        	commentTitle.setText(comment.getTitle() + ":");
        }
        else
        {
        	commentTitle.setVisibility(TextView.GONE);
        }
        commentMessage.setText(comment.getMessage());
        user.setText("Por " + comment.getUser() + " - ");
        commentDate.setText(comment.getDate());
        
        if(false == comment.getUser().equals(Settings.getInstance().getUser()))
        {
        	eraseComment.setVisibility(Button.GONE);
        }
        else
        {
        	final View commentViewToRemove = commentView;
        	eraseComment.setOnClickListener(new OnClickListener()
        	{
				@Override
				public void onClick(View v)
				{
					removeComment(comment, commentViewToRemove);
				}
			});
        }
        linearLayout.addView(commentView);
	}

	protected void removeComment(IComment comment, View commentViewToRemove)
	{
		comments.remove(comment);
		commentViewToRemove.setVisibility(View.GONE);
		// TODO call service to remove comment 
		// and make visible view again if service does not respond
	}
	
	public void onAddCommentButtonTapped()
	{
    	AddCommentDialogFragment dialog = new AddCommentDialogFragment();
    	dialog.show(getSupportFragmentManager(), "addComment");
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

	@Override
	public void onDialogCommentClick(AddCommentDialogFragment dialog)
	{
		Comment newComment = new Comment();
		newComment.setUser(Settings.getInstance().getUser());
		newComment.setTitle(dialog.getTitle());
		newComment.setMessage(dialog.getMessage());
		newComment.stampTime();
		
		IAddToiletCommentService service = ServicesFactory.createAddToiletCommentService();
		service.addToiletComment(this, newComment);
	}

	@Override
	public void onDialogCancelClick(AddCommentDialogFragment dialog) { }

	@Override
	public void addToiletCommentFinish(IAddToiletCommentService service,
			Comment addedComment)
	{
		appendComment(addedComment);
	}

	@Override
	public void addToiletCommentFinishWithError(
			IAddToiletCommentService service, String errorCode) { }

}
