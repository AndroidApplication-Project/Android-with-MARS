package com.example.androidwithmars;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class NavigationDrawerHeader extends AppCompatActivity {
    private ImageView userImage;
    private TextView userName,userEmail;



    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_drawer_header);
        initViews();

//        userName.setText();

    }
    /*public void updateNavHeader(){}*/

    private void initViews() {
        userImage = findViewById(R.id.user_photo);
        userName = findViewById(R.id.user_full_Name);
        userEmail  = findViewById(R.id.user_email);

    }
}
