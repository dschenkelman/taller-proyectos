package socialtoilet.android.activities.dialogs;

import socialtoilet.android.R;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;


public class CalificationDialogFragment extends DialogFragment
{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        builder.setMessage(R.string.calification_dialog_message)
        		.setView(inflater.inflate(R.layout.dialog_calificate, null))
        		.setPositiveButton(R.string.calification_dialog_calificate, new DialogInterface.OnClickListener()
        		{
        			public void onClick(DialogInterface dialog, int id)
        			{
        				// TODO call service on calificate
        			}
        		})
        		.setNegativeButton(R.string.calification_dialog_cancel, new DialogInterface.OnClickListener()
        		{
        			public void onClick(DialogInterface dialog, int id)
             	    {
        				// User cancelled the dialog
             	    }
        		});
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
