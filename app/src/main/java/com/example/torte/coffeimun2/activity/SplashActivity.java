package com.example.torte.coffeimun2.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.torte.coffeimun2.R;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    private int delay = 5000;

    ImageView imageView;

    Timer timer;
    TimerTask timerTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        imageView = (ImageView)findViewById(R.id.soundImage);
        timer = new Timer();

        timerTask = new TimerTask() {
            @Override
            public void run() {
                SplashActivity.this.finish();
            }
        };
        timer.schedule(timerTask, delay, delay);
    }
}
