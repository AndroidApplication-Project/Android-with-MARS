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


public class TutoNotif extends Fragment {

    ArrayList<String> topicname,topiclink;
    ArrayList<Integer>topicpic;
    ListView list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_tutorial, container, false);
        list   = (ListView)v.findViewById(R.id.listv);

        topicname   =  new ArrayList();
        topicpic    =  new ArrayList();
        topiclink   =  new ArrayList();

        topicname.add("Android Notifications Introduction");
        topicpic.add(R.drawable.message);
        topiclink.add("https://firebasestorage.googleapis.com/v0/b/gurunanankacademy.appspot.com/o/part51.pdf?alt=media&token=5cb197e9-ed92-4206-88b2-2c9aace21125");

        topicname.add("Inbox Style Notification");
        topicpic.add(R.drawable.email);
        topiclink.add("https://firebasestorage.googleapis.com/v0/b/gurunanankacademy.appspot.com/o/part52.pdf?alt=media&token=28ea4759-5c7c-4625-b5ff-ddf198bcfab5");

        topicname.add("Big Text Style Notification");
        topicpic.add(R.drawable.textbox);
        topiclink.add("https://firebasestorage.googleapis.com/v0/b/gurunanankacademy.appspot.com/o/part53.pdf?alt=media&token=f0fa460d-d4da-4603-8130-1ecbe5f77d9e");

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