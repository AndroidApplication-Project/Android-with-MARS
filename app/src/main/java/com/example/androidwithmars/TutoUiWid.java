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


public class TutoUiWid extends Fragment {
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

        topicname.add("Android UI Widgets Introduction");
        topicpic.add(R.drawable.keyboard);
        topiclink.add("https://firebasestorage.googleapis.com/v0/b/androidwithmars.appspot.com/o/part20.pdf?alt=media&token=da6ab979-2efa-4c0e-b188-270923cf5b6a");

        topicname.add("Button");
        topicpic.add(R.drawable.smartphone);
        topiclink.add("https://firebasestorage.googleapis.com/v0/b/androidwithmars.appspot.com/o/part21.pdf?alt=media&token=29119e56-73f7-4a43-b582-c81a66e000a6");

        topicname.add("TextField");
        topicpic.add(R.drawable.textbox);
        topiclink.add("https://firebasestorage.googleapis.com/v0/b/androidwithmars.appspot.com/o/part22.pdf?alt=media&token=642016c8-4cad-4f74-9f70-550889c5201e");

        topicname.add("Image Button");
        topicpic.add(R.drawable.uidid);
        topiclink.add("https://firebasestorage.googleapis.com/v0/b/androidwithmars.appspot.com/o/part23.pdf?alt=media&token=35df0d5c-dd87-41ce-a4af-29b7465fc39c");

        topicname.add("Radio Button");
        topicpic.add(R.drawable.radiobutton);
        topiclink.add("https://firebasestorage.googleapis.com/v0/b/androidwithmars.appspot.com/o/part24.pdf?alt=media&token=c841ee44-00ae-4cae-9936-e77bd4f0c798");

        topicname.add("Check Box");
        topicpic.add(R.drawable.checkbox);
        topiclink.add("https://firebasestorage.googleapis.com/v0/b/androidwithmars.appspot.com/o/part25.pdf?alt=media&token=14f74d4d-4acc-4980-bd85-98890c25b2df");

        topicname.add("Spinners");
        topicpic.add(R.drawable.dropdown);
        topiclink.add("https://firebasestorage.googleapis.com/v0/b/androidwithmars.appspot.com/o/part26.pdf?alt=media&token=b95a10f2-1a3a-46b9-b26d-0f1c0923cd86");


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