package socialtoilet.android.activities.dialogs;

import socialtoilet.android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class AddToiletDialogFragment extends DialogFragment {

    public interface INoticeDialogAddToiletListener {
        public void onDialogPositiveClick(DialogFragment dialog);
    }
	
    private INoticeDialogAddToiletListener mListener;
    
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setMessage("¿Agregar un nuevo toilet en la localización seleccionada?")
        		.setTitle("Crear Toilet")
        		 .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       mListener.onDialogPositiveClick(AddToiletDialogFragment.this);
                   }
               })
        		.setNegativeButton("Cancelar", null);
        return builder.create();
    }
    
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (INoticeDialogAddToiletListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement INoticeDialogAddToiletListener");
        }
    }
}
