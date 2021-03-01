package com.example.androidwithmars;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import Quiz.BeginnerQuizActivity;
import Quiz.InterMediateQuizActivity;


public class QuizFragment extends Fragment {
    private BottomNavigationView bottomNavigationView;
    private Button Beginner, Intermediate, Advanced;
    private MaterialToolbar toolbar;


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_quiz, container, false);




        Button Beginner = (Button) v.findViewById(R.id.btnBeginner);
        Beginner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(v.getContext(), BeginnerQuizActivity.class);
                v.getContext().startActivity(intent);
            }
        });
        Button Intermediate = (Button) v.findViewById(R.id.btnIntermediate);
        Intermediate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(v.getContext(), InterMediateQuizActivity.class);
                v.getContext().startActivity(intent);
            }
        });

        return v;

    }

    private void setSupportActionBar(MaterialToolbar toolbar) {

    }


}









