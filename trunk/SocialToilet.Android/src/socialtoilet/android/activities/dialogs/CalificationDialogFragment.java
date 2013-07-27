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
import android.widget.RatingBar;

public class CalificationDialogFragment extends DialogFragment
{

	private ICalificationDialogDelegate delegate;
	private View dialogView;
	private AlertDialog alertDialog;
	
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        dialogView = inflater.inflate(R.layout.dialog_calificate, null);
        
        
        builder.setMessage(R.string.calification_dialog_message)
        		.setView(dialogView)
        		.setPositiveButton(R.string.calification_dialog_calificate, new DialogInterface.OnClickListener()
        		{
        			public void onClick(DialogInterface dialog, int id)
        			{
            			delegate.onDialogCalificateClick(CalificationDialogFragment.this);
        			}
        		})
        		.setNegativeButton(R.string.calification_dialog_cancel, new DialogInterface.OnClickListener()
        		{
        			public void onClick(DialogInterface dialog, int id)
             	    {
        				delegate.onDialogCalificateClick(CalificationDialogFragment.this);
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
        	delegate = (ICalificationDialogDelegate)activity;
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(activity.toString()
                    + " must implement ICalificationDialogDelegate");
        }
    }
    
    public interface ICalificationDialogDelegate
    {
        public void onDialogCalificateClick(CalificationDialogFragment dialog);
        public void onDialogCancelClick(CalificationDialogFragment dialog);
    }

	public int getUserCalification()
	{
		RatingBar rating = (RatingBar)dialogView.findViewById(R.id.calification_dialog_ratingBar);
		return (int)rating.getRating();
	}
}
