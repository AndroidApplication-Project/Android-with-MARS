package com.example.androidwithmars;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainFragment extends Fragment {
    private static final String TAG = "MainFragment";

    private BottomNavigationView bottomNavigationView;
    private RecyclerView beginnerRecView, intermediateRecView, advancedRecView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main,container,false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {

        bottomNavigationView = view.findViewById(R.id.bottomNavView);
        beginnerRecView = view.findViewById(R.id.beginnerRecView);
        intermediateRecView = view.findViewById(R.id.intermediateRecView);
        advancedRecView = view.findViewById(R.id.advancedRecView);


    }
}
