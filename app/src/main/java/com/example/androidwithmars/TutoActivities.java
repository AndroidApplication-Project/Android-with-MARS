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


public class TutoActivities extends Fragment {

    ArrayList<String> topicname,topiclink;
    ArrayList<Integer>topicpic;
    ListView list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v  = inflater.inflate(R.layout.fragment_tutorial, container, false);
        list   = (ListView)v.findViewById(R.id.listv);

        topicname   =  new ArrayList();
        topicpic    =  new ArrayList();
        topiclink   =  new ArrayList();

        topicname.add("Activity Lifecycle");
        topicpic.add(R.drawable.pc);
        topiclink.add("https://firebasestorage.googleapis.com/v0/b/androidwithmars.appspot.com/o/27.pdf?alt=media&token=a556b8be-872b-4582-af36-c5d900536593");
        topicname.add("What is an Intent?");
        topicpic.add(R.drawable.pc);
        topiclink.add("https://firebasestorage.googleapis.com/v0/b/androidwithmars.appspot.com/o/part28.pdf?alt=media&token=de0fb87b-235c-4f23-b4d0-d7c9afb146e7");

        topicname.add("Fragments in Java");
        topicpic.add(R.drawable.puzzle);
        topiclink.add("https://firebasestorage.googleapis.com/v0/b/androidwithmars.appspot.com/o/part29.pdf?alt=media&token=8dc2ca7a-4688-4c04-9a6d-e18b3d2a0a0a");
        topicname.add("Fragments in XML");
        topicpic.add(R.drawable.puzzle2);
        topiclink.add("https://firebasestorage.googleapis.com/v0/b/androidwithmars.appspot.com/o/part30.pdf?alt=media&token=93b94b33-9d0e-48a0-ab48-367f0be3cb03");

        topicname.add("Inter Fragment Communication");
        topicpic.add(R.drawable.puzzle);
        topiclink.add("https://firebasestorage.googleapis.com/v0/b/androidwithmars.appspot.com/o/part31.pdf?alt=media&token=2928e125-f618-483a-973c-69464d91cc7d");


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