package com.example.androidwithmars;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainFragment extends Fragment {
    private static final String TAG = "MainFragment";

    private BottomNavigationView bottomNavigationView;

    private RecyclerView beginnerRecView, intermediateRecView, advancedRecView;
    //This is our tablayout
    private TabLayout tabLayout;

    //This is our viewPager
    private ViewPager viewPager;


    ViewPagerAdapter adapter;

    Tutorial1 frag1;
    VideoFragment frag2;
    QuizFragment frag3;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        initViews(view);

        viewPager = (ViewPager)view.findViewById(R.id.viewpager);

        viewPager.setOffscreenPageLimit(2);



        setupViewPager(viewPager);
        //Initializing the tablayout
        tabLayout = (TabLayout)view.findViewById(R.id.tablayout);

        TabLayout.Tab tab = tabLayout.getTabAt(0);
        tab.select();

        viewPager.setCurrentItem(0,true);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {



            }

            @Override
            public void onPageSelected(int i) {

                tabLayout.getTabAt(i).select();
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));

        return view;
    }



    private void initViews(View view) {
    }

    private void setupViewPager(ViewPager viewPager)
    {
        adapter = new ViewPagerAdapter(getChildFragmentManager());

        frag1  = new Tutorial1();
        frag2  = new VideoFragment();
        frag3  = new QuizFragment();

        adapter.addFragment(frag1,"Tutorial");
        adapter.addFragment(frag2,"Videos");
        adapter.addFragment(frag3,"Quiz");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();

        setupViewPager(viewPager);
    }


}
