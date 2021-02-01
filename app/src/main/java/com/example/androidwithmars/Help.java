package com.example.androidwithmars;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Help extends AppCompatActivity {

    EditText et;
    Button btn;
    String msg="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        et  = (EditText)findViewById(R.id.editTextTextPersonName);
        btn = (Button)findViewById(R.id.button);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                msg  = et.getText().toString();
                if(msg.equals(""))
                {
                    et.requestFocus();
                    et.setError("Fill this field...");
                }
                else
                {

                    try {


                        Intent intent = new Intent(Intent.ACTION_SENDTO); // it's not ACTION_SEND
                        intent.putExtra(Intent.EXTRA_SUBJECT, "Query From App");
                        intent.putExtra(Intent.EXTRA_TEXT, msg);
                        intent.setData(Uri.parse("Monikachattai34@gmail.com")); // or just "mailto:" for blank
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // this will make such that when user returns to your app, your app is displayed, instead of the email app.
                        startActivity(intent);
                    }
                    catch (Exception e)
                    {

                    }
                }
            }
        });

    }
}