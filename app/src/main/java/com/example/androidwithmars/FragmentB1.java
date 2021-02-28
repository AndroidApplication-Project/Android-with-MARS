package com.example.androidwithmars;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class FragmentB1  extends AppCompatActivity {
    TextView textView;
    private static FragmentB1 instance;
    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_b1);
//
//
//    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_b1);

        Intent intent = getIntent();

        String type = intent.getStringExtra("type");
        Toast.makeText(FragmentB1.this, type, Toast.LENGTH_SHORT).show();
        myMethod(type);
    }
    public void myMethod(String id) {
        String yourString;
        // do something...
        TextView tv = (TextView)findViewById(R.id.txtExample);
        switch (id){
            case "textview":
                yourString = getResources().getString(R.string.textview);
                tv.setText(yourString );
                break;
            case "edittext":
            yourString = getResources().getString(R.string.edittext);
            tv.setText(yourString );
            break;
            case "button":
                yourString = getResources().getString(R.string.button);
                tv.setText(yourString );
                break;
                case "radiobutton":
                yourString = getResources().getString(R.string.radiobutton);
                tv.setText(yourString );
                break;
                case "checkbox":
                yourString = getResources().getString(R.string.checkbox);
                tv.setText(yourString );
                break;
                case "ratingbar":
                yourString = getResources().getString(R.string.ratingbar);
                tv.setText(yourString );
                break;
                case "progressbar":
                yourString = getResources().getString(R.string.progressbar);
                tv.setText(yourString );
                break;
                case "seekbar":
                yourString = getResources().getString(R.string.seekbar);
                tv.setText(yourString );
                break;
                case "toggleButton":
                yourString = getResources().getString(R.string.toggleButton);
                tv.setText(yourString );
                break;
                case "spinner":
                yourString = getResources().getString(R.string.spinner);
                tv.setText(yourString );
                break;

        }


    }
    }

