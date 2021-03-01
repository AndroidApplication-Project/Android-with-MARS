package com.example.androidwithmars;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class Tutorial1 extends Fragment {


    GridView grid;
    ArrayList<String>tname;
    ArrayList<Integer>timg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v  = inflater.inflate(R.layout.fragment_tutorial1, container, false);
        grid    = (GridView)v.findViewById(R.id.grid);

        tname  = new ArrayList();
        timg   = new ArrayList();

        tname.add("Introduction");
        timg.add(R.drawable.android1);

        tname.add("Project Structure");
        timg.add(R.drawable.pstrt);

        tname.add("Layouts");
        timg.add(R.drawable.ulay);

        tname.add("UI Widgets");
        timg.add(R.drawable.uwid);

        tname.add("Activites & Fragments");
        timg.add(R.drawable.acti);

        tname.add("Menu");
        timg.add(R.drawable.mnu);

        tname.add("Containers");
        timg.add(R.drawable.contain);

        tname.add("Data Storgae");
        timg.add(R.drawable.stor);

        tname.add("Threads & Process");
        timg.add(R.drawable.thhp);

        tname.add("Notifications");
        timg.add(R.drawable.bell);

        TAdapter adap = new TAdapter(getActivity(),timg,tname);
        grid.setAdapter(adap);
        adap.notifyDataSetChanged();

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0)
                {
                    Tutorial frag = new Tutorial();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.container, frag,
                            frag.getClass().getSimpleName());
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
                else if(i==1)
                {
                    TutoProStrut frag = new TutoProStrut();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.container, frag);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
                else if(i==2)
                {
                    TutoViews frag = new TutoViews();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.container, frag);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
                else if(i==3)
                {
                    TutoUiWid frag = new TutoUiWid();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.container, frag);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }

                else if(i==4)
                {
                    TutoActivities frag = new TutoActivities();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.container, frag);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }

                else if(i==5)
                {
                    TutoMenus frag = new TutoMenus();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.container, frag);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
                else if(i==6)
                {
                    TutoContain frag = new TutoContain();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.container, frag);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
                else if(i==7)
                {
                    TutoData frag = new TutoData();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.container, frag);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
                else if(i==8)
                {
                    TutoThreads frag = new TutoThreads();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.container, frag);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
                else if(i==9)
                {
                    TutoNotif frag = new TutoNotif();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.container, frag);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            }
        });
        return v;

    }

    public class TAdapter extends ArrayAdapter {

        ArrayList<Integer>pics1;
        ArrayList<String>tuname1;
        public TAdapter(Activity activity, ArrayList<Integer>pics1,
                        ArrayList<String> tuname1) {
            super(activity, R.layout.tutodes,tuname1);

            this.tuname1 = tuname1;
            this.pics1    = pics1;
        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            //We are showing custom layout
            LayoutInflater lf = getLayoutInflater();
            View v = lf.inflate(R.layout.tutodes, null, false);
            //Link components of Customizes layout

            ImageView iv = (ImageView)v.findViewById(R.id.imageView5);
            TextView tv5 = (TextView)v.findViewById(R.id.textView5);

            iv.setImageResource(pics1.get(position));
            tv5.setText(tuname1.get(position));

            return v;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        TAdapter adap = new TAdapter(getActivity(),timg,tname);
        grid.setAdapter(adap);
        adap.notifyDataSetChanged();

    }
}