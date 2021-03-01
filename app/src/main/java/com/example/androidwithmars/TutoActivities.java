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
        topiclink.add("https://firebasestorage.googleapis.com/v0/b/gurunanankacademy.appspot.com/o/27.pdf?alt=media&token=c6ee87d8-d896-4330-93a0-31662c4d2a55");

        topicname.add("What is an Intent?");
        topicpic.add(R.drawable.pc);
        topiclink.add("https://firebasestorage.googleapis.com/v0/b/gurunanankacademy.appspot.com/o/part28.pdf?alt=media&token=37681498-aa22-4ddd-b27c-83983eb171ad");

        topicname.add("Fragments in Java");
        topicpic.add(R.drawable.puzzle);
        topiclink.add("https://firebasestorage.googleapis.com/v0/b/gurunanankacademy.appspot.com/o/part29.pdf?alt=media&token=b80ee591-7818-4950-9fd6-2f8f275ca23b");

        topicname.add("Fragments in XML");
        topicpic.add(R.drawable.puzzle2);
        topiclink.add("https://firebasestorage.googleapis.com/v0/b/gurunanankacademy.appspot.com/o/part30.pdf?alt=media&token=3326c42e-e2ba-456b-8934-0447df40c747");

        topicname.add("Inter Fragment Communication");
        topicpic.add(R.drawable.puzzle);
        topiclink.add("https://firebasestorage.googleapis.com/v0/b/gurunanankacademy.appspot.com/o/part31.pdf?alt=media&token=a07270f6-f8a1-4a4d-9f78-b5eeef7f552b");

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