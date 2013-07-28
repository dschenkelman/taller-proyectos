package socialtoilet.android.activities.dialogs;

import socialtoilet.android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;

public class CalificationDialogFragment extends DialogFragment
	implements OnRatingBarChangeListener
{
	// http://developer.android.com/guide/topics/ui/dialogs.html

	private ICalificationDialogDelegate delegate;
	private ICalificationDialogDataSource datasource;
	private View dialogView;
	private AlertDialog alertDialog;
	
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        dialogView = inflater.inflate(R.layout.dialog_calificate, null);

        RatingBar rating = (RatingBar)dialogView.findViewById(R.id.calification_dialog_ratingBar);
        if(null != datasource)
        {
        	float calification = datasource.getUserCalification();
        	Log.d("Social Toilet", "Iniciando rating bar con " + calification);
        	if(0 != calification)
        		rating.setRating(calification);
        }
        rating.setOnRatingBarChangeListener(this);

        builder.setMessage(R.string.calification_dialog_message)
        		.setView(dialogView)
        		.setPositiveButton(R.string.calification_dialog_calificate, new DialogInterface.OnClickListener()
        		{
        			public void onClick(DialogInterface dialog, int id)
        			{
        				if(null != delegate)
            			{
        					delegate.onDialogCalificateClick(CalificationDialogFragment.this);
            			}
        			}
        		})
        		.setNegativeButton(R.string.calification_dialog_cancel, new DialogInterface.OnClickListener()
        		{
        			public void onClick(DialogInterface dialog, int id)
             	    {
        				if(null != delegate)
        				{
        					delegate.onDialogCalificateClick(CalificationDialogFragment.this);
        				}
             	    }
        		});
        alertDialog = builder.create();

        return alertDialog;
    }

	public int getUserCalification()
	{
		RatingBar rating = (RatingBar)dialogView.findViewById(R.id.calification_dialog_ratingBar);
		return (int)rating.getRating();
	}

	@Override
	public void onRatingChanged(RatingBar ratingBar, float arg1, boolean arg2)
	{
    	Button positiveButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
    	positiveButton.setEnabled(0 != (int) ratingBar.getRating());
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
        try
        {
        	datasource = (ICalificationDialogDataSource)activity;
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(activity.toString()
                    + " must implement ICalificationDialogDataSource");
        }
    }

    public interface ICalificationDialogDelegate
    {
        void onDialogCalificateClick(CalificationDialogFragment dialog);
        void onDialogCancelClick(CalificationDialogFragment dialog);
    }
    
    public interface ICalificationDialogDataSource
    {
        float getUserCalification();
    }

}
