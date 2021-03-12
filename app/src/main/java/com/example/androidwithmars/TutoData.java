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


public class TutoData extends Fragment {

    ArrayList<String> topicname,topiclink;
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

        topicname.add("DataStorage");
        topicpic.add(R.drawable.folder1);
        topiclink.add("https://firebasestorage.googleapis.com/v0/b/androidwithmars.appspot.com/o/part41.pdf?alt=media&token=585cab79-9f52-4b28-917b-df3ab7d0fa0e");

        topicname.add("Shared Preference");
        topicpic.add(R.drawable.folder);
        topiclink.add("https://firebasestorage.googleapis.com/v0/b/androidwithmars.appspot.com/o/part42.pdf?alt=media&token=9c0e4481-af67-4bb5-a4dd-e749f9d5a2d1");

        topicname.add("Internal Storage");
        topicpic.add(R.drawable.dataserver);
        topiclink.add("https://firebasestorage.googleapis.com/v0/b/androidwithmars.appspot.com/o/part43.pdf?alt=media&token=e8d48f06-bd4c-4325-8b52-4c569d60977a");

        topicname.add("External Storage");
        topicpic.add(R.drawable.filestorage);
        topiclink.add("https://firebasestorage.googleapis.com/v0/b/androidwithmars.appspot.com/o/part44.pdf?alt=media&token=5be8e1e8-e79e-464d-9a15-3bcbc017e73e");

        topicname.add("SQLite");
        topicpic.add(R.drawable.folder1);
        topiclink.add("https://firebasestorage.googleapis.com/v0/b/androidwithmars.appspot.com/o/part45.pdf?alt=media&token=cc276f92-ebcb-4a78-a537-fb11ac543ebb");


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