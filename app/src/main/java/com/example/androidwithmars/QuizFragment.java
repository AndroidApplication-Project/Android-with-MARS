package com.example.androidwithmars;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class QuizFragment extends Fragment {
    private static final String TAG = "MainFragment";

    private RecyclerView beginnerRecView, intermediateRecView, advancedRecView;


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        initViews(view);
        return view;
    }
    private void initViews(View view) {

        beginnerRecView = view.findViewById(R.id.beginnerRecView);
        intermediateRecView = view.findViewById(R.id.intermediateRecView);
        advancedRecView = view.findViewById(R.id.advancedRecView);


    }

    }


