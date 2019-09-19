package com.example.cerrarsesion.inactividad;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class BaseActivity extends AppCompatActivity{
    private final static String TAG = BaseActivity.class.getSimpleName();
    SessionExpired sessionExpired = new SessionExpired();
    UserInactivity userInactivity = new UserInactivity();


    //    si la actividad se reanuda, el tiempo se detiene
    @Override
    protected void onStart() {
        super.onStart();
        sessionExpired.StartActivity(this);
        Log.e(TAG, "onStart");
    }

    //    si el ciclo de vida de la actividad se pasa a background, el tiempo empieza a correr
    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop");
        Log.e(TAG, "tiempo iniciado");
        sessionExpired.startTime();
        userInactivity.startTop();
        //  startTime();
    }

    // si el cliclo de vida se destruye por medio de un boton que pasa a otra actividad
//    entonces el tiempo queda detenido, para que no haya conflictos con las actividades que extiende
//    con esta BaseActivity
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy");
        sessionExpired.startTop();
        userInactivity.startTop();

    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        Log.e(TAG, "onResume");
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        Log.e(TAG, "onPause");
//    }
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        Log.e(TAG, "onCreate");
//    }
//
//    @Override
//    protected void onRestart() {
//        super.onRestart();
//        Log.e(TAG, "onRestart");
//    }

    //cuando inicia la aplicacion y la vista esta activa y hay interaccion algun toque se activa este metodo
    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
        userInactivity.startTop();
        userInactivity.startTime(this);
    }

    //metodo para verificar si la actividad esta activa
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if(hasFocus){
            Log.e("focus","activo");
            onUserInteraction();
        }else{
            Log.e("focus","desactivado");
            userInactivity.startTop();
        }

        super.onWindowFocusChanged(hasFocus);
    }

}
