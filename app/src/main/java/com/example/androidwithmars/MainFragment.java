package com.example.androidwithmars;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
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
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        initViews(view);

        initBottomNavView();
        return view;
    }

    private void initBottomNavView() {
        // TODO: 26/1/21 Complete Later 
        bottomNavigationView.setSelectedItemId(R.id.videos);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("ResourceType")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.search:
                        Toast.makeText(getActivity(), "Search Selected", Toast.LENGTH_SHORT).show();
                        break;
                        
                    case R.id.videos:
                        Toast.makeText(getActivity(), "Videos Selected", Toast.LENGTH_SHORT).show();
//                        Fragment fragment6 = new VideoFragment();
//                        startActivity(fragment6);
//                        break;

                        break;

                    case R.id.documents:
                        Toast.makeText(getActivity(), "Documents Selected", Toast.LENGTH_SHORT).show();
                        break;

                    default:
                        break;
                }


                return false;


            }

//

        });





    }

    private void initViews(View view) {

        bottomNavigationView = view.findViewById(R.id.bottomNavView);
        beginnerRecView = view.findViewById(R.id.beginnerRecView);
        intermediateRecView = view.findViewById(R.id.intermediateRecView);
        advancedRecView = view.findViewById(R.id.advancedRecView);


    }
}
