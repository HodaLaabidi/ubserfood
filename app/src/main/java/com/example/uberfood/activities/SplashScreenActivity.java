package com.example.uberfood.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.uberfood.R;

public class SplashScreenActivity extends AppCompatActivity {

    private static final long SPLASH_SCREEN_TIMER = 3500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent (SplashScreenActivity.this , FirstScreenActivity.class);
                startActivity(intent);
                finish();

            }
        } , SPLASH_SCREEN_TIMER);
    }
}
