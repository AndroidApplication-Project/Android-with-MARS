package com.example.androidwithmars;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class InterviewQuestion extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Question> questionList;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interview_question);

        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar actionbar=getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_nav_menu);




        recyclerView=findViewById(R.id.myRecyclerView);
        initData();
        setRecyclerView();

    }

    private void setRecyclerView() {
        QuestionAdapter questionAdapter=new QuestionAdapter(questionList);
        recyclerView.setAdapter(questionAdapter);
        recyclerView.setHasFixedSize(true);
    }

    private void initData() {
        questionList=new ArrayList<>();
        questionList.add(new Question(" What is Android?","It is an open-sourced operating system that is used primarily on mobile devices, such as cell phones and tablets. It is a Linux kernel-based system that's been equipped with rich components that allows developers to create and run apps that can perform both basic and advanced functions."));
        questionList.add(new Question(" What Is the Google Android SDK?","The Google Android SDK is a toolset that developers need in order to write apps on Android enabled devices. It contains a graphical interface that emulates an Android driven handheld environment, allowing them to test and debug their codes."));
        questionList.add(new Question(" What is the Android Architecture?","Android Architecture is made up of 4 key components:\n" +
                "\n" +
                "Linux Kernel\n" +
                "Libraries\n" +
                "Android Framework\n" +
                "Android Applications"));
        questionList.add(new Question(" What is AAPT?","AAPT is short for Android Asset Packaging Tool. This tool provides developers with the ability to deal with zip-compatible archives, which includes creating, extracting as well as viewing its contents."));
        questionList.add(new Question(" What is the importance of having an emulator within the Android environment?","The emulator lets developers \"play\" around an interface that acts as if it were an actual mobile device. They can write and test codes, and even debug. Emulators are a safe place for testing codes especially if it is in the early design phase."));
        questionList.add(new Question(" What is the use of an activityCreator?","An activityCreator is the first step towards the creation of a new Android project. It is made up of a shell script that will be used to create new file system structure necessary for writing codes within the Android IDE."));
        questionList.add(new Question(" Describe Activities.","Activities are what you refer to as the window to a user interface. Just as you create windows in order to display output or to ask for an input in the form of dialog boxes, activities play the same role, though it may not always be in the form of a user interface."));
        questionList.add(new Question(" What are Intents?","Intents displays notification messages to the user from within the Android enabled device. It can be used to alert the user of a particular state that occurred. Users can be made to respond to intents."));
        questionList.add(new Question(" Differentiate Activities from Services.","Activities can be closed, or terminated anytime the user wishes. On the other hand, services are designed to run behind the scenes, and can act independently. Most services run continuously, regardless of whether there are certain or no activities being executed."));
        questionList.add(new Question(" What is the importance of XML-based layouts?","The use of XML-based layouts provides a consistent and somewhat standard means of setting GUI definition format. In common practice, layout details are placed in XML files while other items are placed in source files."));
        questionList.add(new Question(" What are containers?","Containers, as the name itself implies, holds objects and widgets together, depending on which specific items are needed and in what particular arrangement that is wanted. Containers may hold labels, fields, buttons, or even child containers, as examples."));
        questionList.add(new Question(" What are the four essential states of an activity?","Active – if the activity is at the foreground\n" +
                "Paused – if the activity is at the background and still visible\n" +
                "Stopped – if the activity is not visible and therefore is hidden or obscured by another activity\n" +
                "Destroyed – when the activity process is killed or completed terminated"));
        questionList.add(new Question("What is the function of an intent filter?","Because every component needs to indicate which intents they can respond to, intent filters are used to filter out intents that these components are willing to receive. One or more intent filters are possible, depending on the services and activities that is going to make use of it."));
        questionList.add(new Question("What role does Dalvik play in Android development?","Dalvik serves as a virtual machine, and it is where every Android application runs. Through Dalvik, a device is able to execute multiple virtual machines efficiently through better memory management."));
        questionList.add(new Question(" What is the AndroidManifest.xml?","This file is essential in every application. It is declared in the root directory and contains information about the application that the Android system must know before the codes can be executed."));
        questionList.add(new Question("What is a Fragment?","A fragment is a part or portion of an activity. It is modular in a sense that you can move around or combine with other fragments in a single activity. Fragments are also reusable."));
        questionList.add(new Question("Is it possible to use or add a fragment without using a user interface?","Yes, it is possible to do that, such as when you want to create a background behavior for a particular activity. You can do this by using add(Fragment,string) method to add a fragment from the activity."));
        questionList.add(new Question("What composes a typical Android application project?","A project under Android development, upon compilation, becomes an .apk file. This apk file format is actually made up of the AndroidManifest.xml file, application code, resource files, and other related files."));
        questionList.add(new Question(" What language is supported by Android for application development?","The main language supported is Java programming language. Java is the most popular language for app development, which makes it ideal even for new Android developers to quickly learn to create and deploy applications in the Android environment."));
        questionList.add(new Question("What is the difference between a regular bitmap and a nine-patch image?","In general, a Nine-patch image allows resizing that can be used as background or other image size requirements for the target device. The Nine-patch refers to the way you can resize the image: 4 corners that are unscaled, 4 edges that are scaled in 1 axis, and the middle one that can be scaled into both axes."));
        questionList.add(new Question("What is an action?","In Android development, an action is what the intent sender wants to do or expected to get as a response. Most application functionality is based on the intended action."));
        questionList.add(new Question("What is a Sticky Intent?","A Sticky Intent is a broadcast from sendStickyBroadcast() method such that the intent floats around even after the broadcast, allowing others to collect data from it."));



    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                Intent next2= new Intent(InterviewQuestion.this, MainActivity.class);
                startActivity(next2);
                break;


        }
        return super.onOptionsItemSelected(item);
    }
}