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


public class TutoProStrut extends Fragment {

    ArrayList<String> topicname,topiclink;
    ArrayList<Integer>topicpic;
    ListView list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v =  inflater.inflate(R.layout.fragment_tutorial, container, false);

        list   = (ListView)v.findViewById(R.id.listv);

        topicname   =  new ArrayList();
        topicpic    =  new ArrayList();
        topiclink   =  new ArrayList();

        topicname.add("Project Structure Introduction");
        topicpic.add(R.drawable.tournament);
        topiclink.add("https://firebasestorage.googleapis.com/v0/b/gurunanankacademy.appspot.com/o/part13.pdf?alt=media&token=e0e4f9f7-9b71-492b-bea5-87814dd42fa2");

        topicname.add("App Module");
        topicpic.add(R.drawable.motherboard);
        topiclink.add("https://firebasestorage.googleapis.com/v0/b/gurunanankacademy.appspot.com/o/part14.pdf?alt=media&token=ae875e8b-0536-4b7f-9fcb-73f54886e003");

        topicname.add("Manifest");
        topicpic.add(R.drawable.petition);
        topiclink.add("https://firebasestorage.googleapis.com/v0/b/gurunanankacademy.appspot.com/o/part15.pdf?alt=media&token=e5721675-51b1-4ee6-8d86-fb5a73ca1f51");

        topicname.add("Java Source Code");
        topicpic.add(R.drawable.java);
        topiclink.add("https://firebasestorage.googleapis.com/v0/b/gurunanankacademy.appspot.com/o/part16.pdf?alt=media&token=0937099f-a5f6-40d9-bef0-39093f8c82eb");

        topicname.add("App Resources");
        topicpic.add(R.drawable.descriptor);
        topiclink.add("https://firebasestorage.googleapis.com/v0/b/gurunanankacademy.appspot.com/o/part17.pdf?alt=media&token=f76aa533-6673-48a9-99cd-16242a436cd3");


        TopicsAdapter adap = new TopicsAdapter(getActivity(),topicpic,topicname,topiclink);
        list.setAdapter(adap);
        adap.notifyDataSetChanged();



        return  v ;
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