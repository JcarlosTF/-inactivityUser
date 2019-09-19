package com.example.cerrarsesion.inactividad;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class DialogoSeleccion extends DialogFragment {

    UserInactivity userInactivity;
    private final static String TAG = DialogoSeleccion.class.getSimpleName();
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            userInactivity = new UserInactivity();
            Log.e(TAG, "activo onCreateDialog");
            final String[] items = {"Español", "Inglés", "Francés"};

            AlertDialog.Builder builder =
                    new AlertDialog.Builder(getActivity());
            builder.setTitle("Selección")
                    .setItems(items, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int item) {
                            Log.i("Dialogos", "Opción elegida: " + items[item]);
                        }
                    });

            return builder.create();
        }

    @Override
    public void onResume() {
        super.onResume();
        userInactivity.startTime(getActivity());
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        userInactivity.startTop();
    }
}
