package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.animation.Animation;

public class MainActivity extends AppCompatActivity {

    private Chronometer chronometer;
    private boolean running;
    private long elapsedTime;
    private ImageView clock;
    private Animation animation;
    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chronometer = findViewById(R.id.chronometer);
        clock = findViewById(R.id.clock);
        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.clockwise);
        startButton = findViewById(R.id.start_button);
        running = false;
    }

    public void start(View view) {

        if(!running){
            chronometer.setBase(SystemClock.elapsedRealtime() - elapsedTime);
            chronometer.start();
            running = true;
            clock.startAnimation(animation);
            startButton.setText("PAUSE");
        }else{
            elapsedTime = SystemClock.elapsedRealtime() - chronometer.getBase();
            chronometer.stop();
            running = false;
            clock.clearAnimation();
            startButton.setText("START");
        }
    }


    public void reset(View view) {
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.stop();
        elapsedTime = 0;
        running = false;
        clock.clearAnimation();

    }
}