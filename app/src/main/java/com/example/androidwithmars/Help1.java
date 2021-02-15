package com.example.androidwithmars;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class Help1 extends Fragment {
    //
    EditText et;
    Button btn;
    String msg="";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_help1, container, false);

        et  = (EditText)v.findViewById(R.id.editTextTextPersonName);
        btn = (Button)v.findViewById(R.id.button);


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

                        Intent email = new Intent(Intent.ACTION_SEND);
                        email.putExtra(Intent.EXTRA_EMAIL,new String[]{ "mad318group2@gmail.com"});
                        email.putExtra(Intent.EXTRA_SUBJECT, "Query From Android with Mars");
                        email.putExtra(Intent.EXTRA_TEXT, msg);

                        //need this to prompts email client only
                        email.setType("message/rfc822");
                        startActivity(Intent.createChooser(email, "Choose an Email client :"));


                    }
                    catch (Exception e)
                    {

                    }
                }
            }
        });

        return v;
    }
}