package socialtoilet.android.activities.dialogs;

import socialtoilet.android.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class AddCommentDialogFragment extends DialogFragment
{
    
    private IAddCommentDialogDelegate delegate;
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
    }
	
    public interface IAddCommentDialogDelegate
    {
        void onDialogCommentClick(AddCommentDialogFragment dialog);
        void onDialogCancelClick(AddCommentDialogFragment dialog);
    }
    
	public String getTitle()
	{
		EditText title = (EditText) dialogView.findViewById(R.id.title);
		return title.getText().toString();
	}

	public String getMessage()
	{
		EditText comment = (EditText) dialogView.findViewById(R.id.comment);
		return comment.getText().toString();
	}
}
