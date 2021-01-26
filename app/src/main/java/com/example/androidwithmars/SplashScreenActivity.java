package com.example.androidwithmars;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import gr.net.maroulis.library.EasySplashScreen;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EasySplashScreen splashScreen = new EasySplashScreen(SplashScreenActivity.this)
                .withFullScreen()
                .withTargetActivity(RegisterUser.class)
                .withSplashTimeOut(5000)
                .withBackgroundColor(Color.parseColor("#000000"))
                .withHeaderText("Welcome")
//                .withFooterText("Android With Mars")
//                .withBeforeLogoText("Before Logo")
//                .withAfterLogoText("After Logo")
                .withLogo(R.mipmap.splash);


        splashScreen.getHeaderTextView().setTextColor(Color.WHITE);

        View easySplash  = splashScreen.create();
        setContentView(easySplash);

    }
}