package com.example.cerrarsesion.inactividad;


import android.app.Activity;
import android.content.Intent;
import android.os.CountDownTimer;
import android.util.Log;

import com.example.cerrarsesion.MainActivity;

public class SessionExpired {
    /**
     * Nota
     * una hora = 60 minutos = 60 segundos
     * un segundo = 1000 milisegundos
     **/
    private final static String TAG = SessionExpired.class.getSimpleName();
    private final static long timer = 10000;
    private CountDownTimer countDownTimer;
    private long timeLeftInMilliseconds = timer;
    private boolean timerRunning;
    private boolean timerFinish;

    public void startTime() {
        //numero en milisegundos, desde la llamada startTime() hasta que se complete la cuenta regresiva en onFinish()
        countDownTimer = new CountDownTimer(timeLeftInMilliseconds, 1000) {
            //cuando se inicia una cuenta regresiva, este método se ejecutará
            // y podremos mostrar el tiempo sobre un TextView o Log.
            @Override
            public void onTick(long l) {
                timeLeftInMilliseconds = l;
                updateTimer();
            }

            //metodo que se llama cuando CountDownTimer termine
            @Override
            public void onFinish() {
                Log.e(TAG, "tiempo expirado");
                timerFinish = true;
            }
        }.start();
        timerRunning = true;
        timerFinish = false;
    }


    //este metodo se utiliza para cancelar CountDownTimer
    public void stopTimer() {
        countDownTimer.cancel();
        timerRunning = false;
        Log.e(TAG, "tiempo detenido");
    }

    //este metodo se utiliza para resetear CountDownTimer
    public void resetTimer() {
        timeLeftInMilliseconds = timer;
        Log.e(TAG, "tiempo reseteado");
    }

    public void startTop() {
        if (timerRunning) {
            stopTimer();
            resetTimer();
        }
    }

    public void updateTimer() {
        String timeLeftText = "";
        int minutos = (int) timeLeftInMilliseconds / 60000;
        int seconds = (int) timeLeftInMilliseconds % 60000 / 1000;
        timeLeftText = "0" + minutos;
        timeLeftText += ":";
        if (seconds < 10) timeLeftText += "0";
        timeLeftText += seconds;

        //int se = (int) (timeLeftInMilliseconds % 60000);
        //Log.e(TAG,"minutos "+minutos+ " "+se);
        Log.e(TAG, "tiempo " + timeLeftText);
    }

    public void StartActivity(Activity activity) {
        if (timerFinish) {
            Log.e(TAG, "timerFinish " + timerFinish);
            activity.startActivity(new Intent(activity, MainActivity.class));
            activity.finish();
        } else {
            startTop();
        }
    }

}
