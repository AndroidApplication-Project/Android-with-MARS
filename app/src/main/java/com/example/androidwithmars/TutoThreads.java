package com.example.androidwithmars;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class TutoThreads extends Fragment {

    ArrayList<String>topicname,topiclink;
    ArrayList<Integer>topicpic;
    ListView list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tutorial, container, false);
        list   = (ListView)v.findViewById(R.id.listv);

        topicname   =  new ArrayList();
        topicpic    =  new ArrayList();
        topiclink   =  new ArrayList();

        topicname.add("Android Process");
        topicpic.add(R.drawable.ic_processing7);
        topiclink.add("https://firebasestorage.googleapis.com/v0/b/androidwithmars.appspot.com/o/part46.pdf?alt=media&token=2daabf7f-ece7-45c6-806b-334d15d69bf9");


        topicname.add("Android InterProcess Communication");
        topicpic.add(R.drawable.ic_time8);
        topiclink.add("https://firebasestorage.googleapis.com/v0/b/androidwithmars.appspot.com/o/part47.pdf?alt=media&token=57ceb603-e568-4b17-9cd7-882d47e4f338");

        topicname.add("Async Task");
        topicpic.add(R.drawable.test);
        topiclink.add("https://firebasestorage.googleapis.com/v0/b/androidwithmars.appspot.com/o/part48.pdf?alt=media&token=fa857a3f-5ea3-49fc-b261-523d3b334208");

        topicname.add("Threads");
        topicpic.add(R.drawable.ic_processing7);
        topiclink.add("https://firebasestorage.googleapis.com/v0/b/androidwithmars.appspot.com/o/part49.pdf?alt=media&token=4b750dfe-398a-40fe-9f4b-b4b57dca492d");

        topicname.add("Handler and Looper");
        topicpic.add(R.drawable.pc);
        topiclink.add("https://firebasestorage.googleapis.com/v0/b/androidwithmars.appspot.com/o/part50.pdf?alt=media&token=3950d312-5887-40d4-976b-35d2c500dd5a");


        TopicsAdapter adap = new TopicsAdapter(getActivity(),topicpic,topicname,topiclink);
        list.setAdapter(adap);
        adap.notifyDataSetChanged();

        return v;
    }

    public class TopicsAdapter extends ArrayAdapter {

        ArrayList<Integer>pics1;
        ArrayList<String>topics1,topiclink1;
        public TopicsAdapter(Activity activity, ArrayList<Integer>pics1,
                             ArrayList<String>topics1, ArrayList<String>topiclink1) {
            super(activity, R.layout.showcourse,topics1);

            this.topics1  = topics1;
            this.pics1    = pics1;
            this.topiclink1 = topiclink1;
        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            //We are showing custom layout
            LayoutInflater lf = getLayoutInflater();
            View v = lf.inflate(R.layout.showcourse, null, false);
            //Link components of Customizes layout

            ImageView iv = (ImageView)v.findViewById(R.id.imageView4);
            TextView tv12 = (TextView)v.findViewById(R.id.textView12);
            ConstraintLayout cl = (ConstraintLayout)v.findViewById(R.id.laycont);

            iv.setImageResource(pics1.get(position));
            tv12.setText(topics1.get(position));

            cl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent next = new Intent(getContext(),ReadTopic.class);
                    //send link via intent to another activity
                    next.putExtra("link",topiclink1.get(position));
                    startActivity(next);
                }
            });

            return v;
        }
    }

}