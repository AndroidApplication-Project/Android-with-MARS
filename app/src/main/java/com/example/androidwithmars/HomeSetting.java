package com.example.androidwithmars;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeSetting extends AppCompatActivity {
    Button btn1,btn2,btn3,btn4;
    private CircleImageView profileImageView;
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_setting);
        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);
        btn3=findViewById(R.id.btn3);
        btn4=findViewById(R.id.btn4);

        mAuth= FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance("https://androidwithmars-default-rtdb.firebaseio.com/").getReference().child("User");


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeSetting.this,"Edit profile clicked",Toast.LENGTH_SHORT).show();
            }
        });




    }
}