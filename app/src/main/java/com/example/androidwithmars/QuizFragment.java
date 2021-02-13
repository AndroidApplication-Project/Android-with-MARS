package com.example.androidwithmars;

import android.content.DialogInterface;
import android.content.Intent;
import android.location.GnssAntennaInfo;
import android.net.sip.SipSession;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import Quiz.BeginnerQuizActivity;


public class QuizFragment extends Fragment  {
    private BottomNavigationView bottomNavigationView;
    private  TextView Beginner, Intermediate, Advanced;



    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      View v = inflater.inflate(R.layout.fragment_quiz, container, false);

       TextView Beginner = (TextView) v.findViewById(R.id.txtBeginner);
       Beginner.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(v.getContext(),BeginnerQuizActivity.class);
               startActivity(intent);
           }
       });

         return v;

    }


}


