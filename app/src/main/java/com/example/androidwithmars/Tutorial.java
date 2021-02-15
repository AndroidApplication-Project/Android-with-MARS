package com.example.androidwithmars;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Tutorial extends Fragment {
    ArrayList<String>topicname,topiclink;
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

        topicname.add("Android Introduction");
        topicpic.add(R.drawable.ic_android1);
        topiclink.add("https://firebasestorage.googleapis.com/v0/b/androidwithmars.appspot.com/o/part1.pdf?alt=media&token=0e9c5775-73b2-4b3f-a9e3-27714e91d05f");

        topicname.add("Android History and Versions");
        topicpic.add(R.drawable.ic_books2);
        topiclink.add("https://firebasestorage.googleapis.com/v0/b/androidwithmars.appspot.com/o/Part2.pdf?alt=media&token=33e4fc8d-b91c-4a3a-9ac7-20cbab2e77e6");

        topicname.add("Android Version Symbols");
        topicpic.add(R.drawable.ic_check3);
        topiclink.add("https://firebasestorage.googleapis.com/v0/b/androidwithmars.appspot.com/o/part3.pdf?alt=media&token=81c927de-cc2a-426e-a5d9-a1585780a378");

        topicname.add("Why Android ?");
        topicpic.add(R.drawable.ic_notepad4);
        topiclink.add("https://firebasestorage.googleapis.com/v0/b/androidwithmars.appspot.com/o/part4.pdf?alt=media&token=1b2c924a-37e4-4ab0-a6f4-912280d889da");

        topicname.add("Android Software Stack");
        topicpic.add(R.drawable.ic_transip_stack5);
        topiclink.add("https://firebasestorage.googleapis.com/v0/b/androidwithmars.appspot.com/o/part5.pdf?alt=media&token=27d3cb0c-0a84-49ef-b5fb-8ea53ee93296");

        topicname.add("APK Builder");
        topicpic.add(R.drawable.ic_robot6);
        topiclink.add("https://firebasestorage.googleapis.com/v0/b/androidwithmars.appspot.com/o/part6.pdf?alt=media&token=c857427b-78da-427e-bf3a-491cf86859d0");

        topicname.add("Android Runtime");
        topicpic.add(R.drawable.ic_processing7);
        topiclink.add("https://firebasestorage.googleapis.com/v0/b/androidwithmars.appspot.com/o/part7.pdf?alt=media&token=d125a0b9-899e-4eb2-a399-ad6d627bda85");

        topicname.add("Life of an APK");
        topicpic.add(R.drawable.ic_time8);
        topiclink.add("https://firebasestorage.googleapis.com/v0/b/androidwithmars.appspot.com/o/part8.pdf?alt=media&token=9de785f0-0d5e-400f-860c-712a0b6fdd4d");

        topicname.add("Android Application Components");
        topicpic.add(R.drawable.ic_hardware9);
        topiclink.add("https://firebasestorage.googleapis.com/v0/b/androidwithmars.appspot.com/o/part9.pdf?alt=media&token=4db5f94a-08de-40ed-9a67-3414e3b4484c");

        topicname.add("Android Environmental Setup");
        topicpic.add(R.drawable.ic_setup10);
        topiclink.add("https://firebasestorage.googleapis.com/v0/b/androidwithmars.appspot.com/o/part10.pdf?alt=media&token=ca663b37-5590-401f-83a7-da747a0bb23b");

        topicname.add("How to Activate Developers Option");
        topicpic.add(R.drawable.ic_android1);
        topiclink.add("https://firebasestorage.googleapis.com/v0/b/androidwithmars.appspot.com/o/part11.pdf?alt=media&token=5012a162-1be8-4983-b5cd-503695275122");

        topicname.add("Building Your First App");
        topicpic.add(R.drawable.ic_books2);
        topiclink.add("https://firebasestorage.googleapis.com/v0/b/androidwithmars.appspot.com/o/part12.pdf?alt=media&token=686e6969-751e-41f5-bb43-017836711329");

        TopicsAdapter adap = new TopicsAdapter(getActivity(),topicpic,topicname,topiclink);
        list.setAdapter(adap);
        adap.notifyDataSetChanged();

        return v;
    }

    public class TopicsAdapter extends ArrayAdapter {

        ArrayList<Integer>pics1;
        ArrayList<String>topics1,topiclink1;
        public TopicsAdapter(Activity activity, ArrayList<Integer>pics1,
                             ArrayList<String>topics1,ArrayList<String>topiclink1) {
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