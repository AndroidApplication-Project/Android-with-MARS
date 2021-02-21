package com.example.androidwithmars;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class VideoFragment extends Fragment {

    Button beginner, intermediate, advanced;
    LinearLayout selectDifficulty, beginnerVideos, intermediateVideos, advancedVideos;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view =   inflater.inflate(R.layout.fragment_video, container, false);

        initViews(view);

        beginner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beginnerVideos.setVisibility(View.VISIBLE);
                selectDifficulty.setVisibility(View.GONE);
            }
        });

        intermediate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intermediateVideos.setVisibility(View.VISIBLE);
                selectDifficulty.setVisibility(View.GONE);
            }
        });

        advanced.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                advancedVideos.setVisibility(View.VISIBLE);
                selectDifficulty.setVisibility(View.GONE);
            }
        });


        return view;
    }

    private void initViews(View view) {
        beginner = view.findViewById(R.id.btnBeginnerVideo);
        intermediate = view.findViewById(R.id.btnIntermediateVideo);
        advanced = view.findViewById(R.id.btnAdvancedVideo);

        selectDifficulty = view.findViewById(R.id.selectDifficultyLayout);
        beginnerVideos = view.findViewById(R.id.beginnerVideos);
        intermediateVideos = view.findViewById(R.id.intermediateVideos);
        advancedVideos = view.findViewById(R.id.advancedVideos);
    }


}