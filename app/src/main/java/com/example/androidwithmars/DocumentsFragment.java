package com.example.androidwithmars;

import android.content.Intent;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.FileReader;

import Quiz.BeginnerQuizActivity;


public class DocumentsFragment extends Fragment {
private Button button2;
private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private Button button10;
    private Button button11;
TextView textView;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view= inflater.inflate(R.layout.fragment_example, container, false);




        Button button2 = view.findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(view.getContext(), FragmentB1.class);
                myIntent.putExtra("type", "textview");
                startActivity(myIntent);
            }
        });
        Button button3= view.findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(view.getContext(), FragmentB1.class);
                myIntent.putExtra("type", "edittext");
                startActivity(myIntent);
            }
        });

        Button button4= view.findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(view.getContext(), FragmentB1.class);
                myIntent.putExtra("type", "button");
                startActivity(myIntent);
            }
        });

        Button button5= view.findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(view.getContext(), FragmentB1.class);
                myIntent.putExtra("type", "radiobutton");
                startActivity(myIntent);
            }
        });

        Button button6= view.findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(view.getContext(), FragmentB1.class);
                myIntent.putExtra("type", "checkbox");
                startActivity(myIntent);
            }
        });

        Button button7= view.findViewById(R.id.button7);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(view.getContext(), FragmentB1.class);
                myIntent.putExtra("type", "ratingbar");
                startActivity(myIntent);
            }
        });

        Button button8= view.findViewById(R.id.button8);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(view.getContext(), FragmentB1.class);
                myIntent.putExtra("type", "progressbar");
                startActivity(myIntent);
            }
        });

        Button button9= view.findViewById(R.id.button9);
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(view.getContext(), FragmentB1.class);
                myIntent.putExtra("type", "seekbar");
                startActivity(myIntent);
            }
        });

        Button button10= view.findViewById(R.id.button10);
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(view.getContext(), FragmentB1.class);
                myIntent.putExtra("type", "toggleButton");
                startActivity(myIntent);
            }
        });
        Button button11= view.findViewById(R.id.button11);
        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(view.getContext(), FragmentB1.class);
                myIntent.putExtra("type", "spinner");
                startActivity(myIntent);
            }
        });

    return view;

    }



}