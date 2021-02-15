package com.example.androidwithmars;

import android.content.Intent;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;



public class DocumentsFragment extends Fragment {


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Button button = null;
        button.findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(this, ActivityStore.class);
                startActivity(i);
            }
        });
    }



}