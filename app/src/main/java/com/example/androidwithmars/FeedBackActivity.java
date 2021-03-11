package com.example.androidwithmars;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hsalf.smilerating.SmileRating;

public class FeedBackActivity extends AppCompatActivity {

    FirebaseDatabase reference;
    Button feedback;
    EditText Name, Email, Message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);

        SmileRating smileRating = (SmileRating) findViewById(R.id.ratingBar);
        feedback = (Button) findViewById(R.id.SendFB);
        Name =   (EditText) findViewById(R.id.etName);
        Email =   (EditText) findViewById(R.id.etEmail);
        Message =   (EditText) findViewById(R.id.etMessage);
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference reference = rootRef.child("Ratings");


        smileRating.setOnRatingSelectedListener(new SmileRating.OnRatingSelectedListener() {
            @Override
            public void onRatingSelected(int level, boolean reselected) {
                Toast.makeText(getApplicationContext(), "Your feedback value is " + level,
                        Toast.LENGTH_SHORT).show();
                reference.child("Rating").setValue(String.valueOf(level));


            }
        });

        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = Name.getText().toString();
                String email = Email.getText().toString();
                String message = Message.getText().toString();
                Toast.makeText(FeedBackActivity.this, "Feedback Sent! Thank You", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(FeedBackActivity.this,MainActivity.class);
                startActivity(intent);




                DatabaseReference reference1 = reference.child("Name");
                reference1.setValue(name);
                if (name.isEmpty()){
                    Name.setError("This is required field");
                    feedback.setEnabled(false);

                }else{

                    Name.setError(null);
                    feedback.setEnabled(true);
                }

                DatabaseReference reference2 = reference.child("Email");
                reference2.setValue(email);
                if (email.isEmpty()){
                    Email.setError("This is required field");
                    feedback.setEnabled(false);

                }else{
                    Email.setError(null);
                    feedback.setEnabled(true);
                }

                DatabaseReference reference3 = reference.child("Message");
                reference3.setValue(message);
                if (message.isEmpty()){
                    Message.setError("This is required field");
                    feedback.setEnabled(false);

                }else{
                    Message.setError(null);
                    feedback.setEnabled(true);


                }

            }
        });


    }
}