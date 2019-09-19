package com.example.cerrarsesion;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.fragment.app.FragmentManager;

import com.example.cerrarsesion.inactividad.BaseActivity;
import com.example.cerrarsesion.inactividad.DialogoSeleccion;

public class Main2Activity extends BaseActivity {
    Button dialogoSelecion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        dialogoSelecion = findViewById(R.id.dialogo);

        dialogoSelecion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                DialogoSeleccion dialogo = new DialogoSeleccion();

                dialogo.show(fragmentManager, "tagSeleccion");
            }
        });
    }
}
