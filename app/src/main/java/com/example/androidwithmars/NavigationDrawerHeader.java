package com.example.androidwithmars;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class NavigationDrawerHeader extends AppCompatActivity {
    private ImageView userImage;
    private TextView userName,userEmail;
    private RegisterUser registerUser;


    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_drawer_header);
        initViews();

//        userName.setText();


    }

    private void initViews() {
        userImage = findViewById(R.id.userImage);
        userName = findViewById(R.id.userName);
        userEmail  = findViewById(R.id.userEmail);

    }
}
