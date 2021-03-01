package com.example.androidwithmars;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;

import gr.net.maroulis.library.EasySplashScreen;

public class SplashScreenActivity extends AppCompatActivity {

     @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.splash_screen);


         new Handler().postDelayed(new Runnable() {
             @Override public void run() {
                 Intent i = new Intent(SplashScreenActivity.this, MainActivity.class); startActivity(i);
                 finish(); } }, 3000);
     }
    }