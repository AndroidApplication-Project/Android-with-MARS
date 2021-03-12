package com.example.androidwithmars;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class About_Us1 extends Fragment {

    //Declare Object of List View
    ListView list;
    //Declare array to store student image from drawable
    ArrayList<Integer>pics;
    //Array to store student name and Id
    ArrayList<String>stuid,stuname;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_about__us1, container, false);

        //Link these views
        TextView tv3 = (TextView)root.findViewById(R.id.textView3);
        list         = (ListView)root.findViewById(R.id.listv);

//Set Text on Text view , if else used because diff way to show on devices lower than Oreo and
        //new ones
        //We make use of HTML on text view for better aligment
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tv3.setText(Html.fromHtml("<p style=\"font-size:5px;align:justify\">"+"Android " +
                            "With Mars" +
                            " is an Android app developed by gruop of students. Basic idea" +
                            "behind this app was to provide an opportunity and platform " +
                            "to learn basis of android."+"</p>",
                    Html.FROM_HTML_MODE_COMPACT));
        } else {
            tv3.setText(Html.fromHtml("<p style=\"font-size:5px;align:justify\">"+"Android With " +
                    "Mars is an Android app developed by gruop of students. Basic idea\n" +
                    "behind this app was to provide an opportunity and platform to learn basis of" +
                    "android."+"</p>"));
        }

        //Initialize array
        pics    = new ArrayList();
        stuid   = new ArrayList();
        stuname = new ArrayList();

        //add data in arrays
        pics.add(R.drawable.pic1suna);
        stuname.add("Sunayna Sohpaul");
        stuid.add("1993557");

        pics.add(R.drawable.buggu);
        stuname.add("Monika Monika");
        stuid.add("1993500");

        pics.add(R.drawable.akashpic);
        stuname.add("Akash Jha");
        stuid.add("1993392");

        pics.add(R.drawable.sus);
        stuname.add("Sushant Sharma");
        stuid.add("1993387");

        pics.add(R.drawable.raghv);
        stuname.add("Raghav Kasrija ");
        stuid.add("1993919");
        pics.add(R.drawable.pic1suna);
        stuname.add("Manjodh Singh ");
        stuid.add("1993904");

        //Initializing and setting Custom Adapter on List View
        StudentsAdapter adap = new StudentsAdapter(getActivity(),pics,stuid,stuname);
        list.setAdapter(adap);
        adap.notifyDataSetChanged();
        return root;
    }
    //This is a Adapter type class to show data on list view
    //List view is customized

    public class StudentsAdapter extends ArrayAdapter {

        ArrayList<Integer>pics1;
        ArrayList<String>stuid1,stuname1;
        public StudentsAdapter(Activity activity,ArrayList<Integer>pics1,
                               ArrayList<String>stuid1, ArrayList<String> stuname1) {
            super(activity, R.layout.developerlist,stuid1);

            this.stuid1   = stuid1;
            this.stuname1 = stuname1;
            this.pics1    = pics1;
        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            //We are showing custom layout
            LayoutInflater lf = getLayoutInflater();
            View v = lf.inflate(R.layout.developerlist, null, false);
            //Link components of Customizes layout

            ImageView iv = (ImageView)v.findViewById(R.id.imageView3);
            TextView tv2 = (TextView)v.findViewById(R.id.textView2);
            TextView tv10 = (TextView)v.findViewById(R.id.textView10);

            iv.setImageResource(pics1.get(position));
            tv2.setText(stuname1.get(position));
            tv10.setText("StuID : "+stuid1.get(position));


            return v;
        }
    }


}