package com.example.androidwithmars;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ReadTopic extends AppCompatActivity {

    WebView web;
    String url = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_topic);

        web    = (WebView)findViewById(R.id.web);

        web.setWebViewClient(new WebViewClient());
        web.setWebChromeClient(new WebChromeClient());
        web.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        web.getSettings().setJavaScriptEnabled(true);
        web.clearCache(true);

        //way to get data via intent from other activity.

        url  = getIntent().getStringExtra("link");
        try {
            url=URLEncoder.encode(url,"UTF-8");
            web.loadUrl("https://docs.google.com/gview?embedded=true&url="+url);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

}