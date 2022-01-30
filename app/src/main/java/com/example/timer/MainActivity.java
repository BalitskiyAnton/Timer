package com.example.timer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    private int time = 0;
    private Boolean isRunning = false;
    private  Boolean wasRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            time = savedInstanceState.getInt("time");
            isRunning = savedInstanceState.getBoolean("isRunning");
           // wasRunning = savedInstanceState.getBoolean("wasRunning");
        }

        textView = findViewById(R.id.textView);

        runTimer();
    }

   /* @Override
    protected void onStop() {
        super.onStop();
        wasRunning = isRunning;
        isRunning = false;
    }

    @Override
    protected void onStart() {
        super.onStart();
        isRunning = wasRunning;
    }*/

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("time", time);
        outState.putBoolean("isRunning", isRunning);
        outState.putBoolean("wasRunning", wasRunning);
    }

    public void StartTime(View view) {

        isRunning = true;
    }

    public void StopTime(View view) {

        isRunning = false;
    }

    public void ResetTime(View view) {

        isRunning = false;
        time = 0;
    }

    private void runTimer() {
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = time/3600;
                int min =  (time % 3600) / 60;
                int sec = time % 60;

                String timer = String.format(Locale.getDefault(), "%d:%02d:%02d", hours, min, sec);
                textView.setText(timer);

                if (isRunning) {
                    time++;
                }
                handler.postDelayed(this, 1000);
            }
        });


    }
}