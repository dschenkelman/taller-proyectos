package socialtoilet.android.activities.dialogs;

import java.util.UUID;

import socialtoilet.android.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;

public class AddCommentDialogFragment extends DialogFragment
{
    
    private IAddCommentDialogDelegate delegate;
	private IAddCommentDialogDataSource datasource;
	private View dialogView;
	private AlertDialog alertDialog;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        dialogView = inflater.inflate(R.layout.dialog_comment, null);

        builder.setMessage(R.string.comment_dialog_message)
        		.setView(dialogView)
        		.setPositiveButton(R.string.comment_dialog_comment, new DialogInterface.OnClickListener()
        		{
        			public void onClick(DialogInterface dialog, int id)
        			{
        				if(null != delegate)
            			{
        					delegate.onDialogCommentClick(AddCommentDialogFragment.this);
            			}
        			}
        		})
        		.setNegativeButton(R.string.comment_dialog_cancel, new DialogInterface.OnClickListener()
        		{
        			public void onClick(DialogInterface dialog, int id)
             	    {
        				if(null != delegate)
        				{
        					delegate.onDialogCancelClick(AddCommentDialogFragment.this);
        				}
             	    }
        		});
        alertDialog = builder.create();

        return alertDialog;
    }
	
	@Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        try
        {
        	delegate = (IAddCommentDialogDelegate)activity;
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(activity.toString()
                    + " must implement IAddCommentDialogDelegate");
        }
        try
        {
        	datasource = (IAddCommentDialogDataSource)activity;
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(activity.toString()
                    + " must implement IAddCommentDialogDataSource");
        }
    }
	
    public interface IAddCommentDialogDelegate
    {
        void onDialogCommentClick(AddCommentDialogFragment dialog);
        void onDialogCancelClick(AddCommentDialogFragment dialog);
    }
    
    public interface IAddCommentDialogDataSource
    {
        UUID getToiletId();
    }

	public String getTitle()
	{
		// TODO Auto-generated method stub
		return "";
	}

	public String getMessage()
	{
		// TODO Auto-generated method stub
		return "";
	}
}
