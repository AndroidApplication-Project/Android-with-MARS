package Quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidwithmars.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class InterMediateQuizActivity extends AppCompatActivity {

    Button ButtonAnswer1, ButtonAnswer2, ButtonAnswer3, ButtonAnswer4,endQuiz;
    TextView tvquestion, timer;
    int total = 1;
    int correct = 0;
    int incorrect = 0;
    DatabaseReference reference;
    int computerCount=0;
    ArrayList<String> quesid,ques,ans,ans1,ans2,ans3,ans4;
    ProgressDialog prd;
    int tcount=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inter_mediate_quiz);
        reverseTimer(90,timer);
        updateQuestion();


        timer = (TextView) findViewById(R.id.timer);
        tvquestion = (TextView) findViewById(R.id.tvquestion);

        ButtonAnswer1 = (Button) findViewById(R.id.btnanswer1);
        ButtonAnswer2 = (Button) findViewById(R.id.btnanswer2);
        ButtonAnswer3 = (Button) findViewById(R.id.btnanswer3);
        ButtonAnswer4 = (Button) findViewById(R.id.btnanswer4);

        endQuiz = (Button) findViewById(R.id.endQuiz);
        endQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InterMediateQuizActivity.this,ResultActivity.class);
                startActivity(intent);
            }
        });


        prd    = new ProgressDialog(InterMediateQuizActivity.this);
        prd.setMessage("Loading...");
        prd.setCancelable(false);
        prd.show();

        ques   =  new ArrayList();
        quesid =  new ArrayList();
        ans    =  new ArrayList();
        ans1   =  new ArrayList();
        ans2   =  new ArrayList();
        ans3   =  new ArrayList();
        ans4   =  new ArrayList();


        try
        {
            DatabaseReference d = FirebaseDatabase.getInstance().getReference("Questions").child("Intermediate");
            d.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for(DataSnapshot dd : snapshot.getChildren())
                    {
                        String k = dd.getKey();
                        if(!quesid.contains(k))
                        {
                            quesid.add(k);
                        }
                    }
                    Collections.shuffle(quesid);
                    for(int i = 0 ; i<15; i++)
                    {
                        DatabaseReference data = FirebaseDatabase.getInstance().getReference(
                                "Questions").child("Intermediate").child(quesid.get(i));
                        data.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                ques.add(snapshot.child("question").getValue(String.class));
                                ans.add(snapshot.child("answer").getValue(String.class));
                                ans1.add(snapshot.child("answer1").getValue(String.class));
                                ans2.add(snapshot.child("answer2").getValue(String.class));
                                ans3.add(snapshot.child("answer3").getValue(String.class));
                                ans4.add(snapshot.child("answer4").getValue(String.class));


                                tvquestion.setText(ques.get(0));
                                ButtonAnswer1.setText(ans1.get(0));
                                ButtonAnswer2.setText(ans2.get(0));
                                ButtonAnswer3.setText(ans3.get(0));
                                ButtonAnswer4.setText(ans4.get(0));
                                prd.dismiss();

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        catch (Exception e)
        {

        }





        ButtonAnswer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ButtonAnswer1.getText().toString().equals(ans.get(tcount)))
                {
                    Toast.makeText(getApplicationContext(),"Correct answer",Toast.LENGTH_SHORT).show();
                    ButtonAnswer1.setBackgroundColor(Color.GREEN);

                    Handler handler = new Handler();

                    handler.postDelayed(new Runnable() {
                        public void run() {
                            correct++;
                            ButtonAnswer1.setBackgroundColor(Color.parseColor("#03A9F4"));
                            updateQuestion();
                        }
                    }, 1500);

                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Incorrect",Toast.LENGTH_SHORT).show();
                    incorrect = incorrect + 1;

                    ButtonAnswer1.setBackgroundColor(Color.RED);

                    if(ButtonAnswer2.getText().toString().equals(ans.get(tcount)))
                    {
                        ButtonAnswer2.setBackgroundColor(Color.GREEN);
                    }
                    else if(ButtonAnswer3.getText().toString().equals(ans.get(tcount)))
                    {
                        ButtonAnswer3.setBackgroundColor(Color.GREEN);
                    }
                    else if(ButtonAnswer4.getText().toString().equals(ans.get(tcount)))
                    {
                        ButtonAnswer4.setBackgroundColor(Color.GREEN);
                    }



                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            ButtonAnswer1.setBackgroundColor(Color.parseColor("#03A9F4"));
                            ButtonAnswer2.setBackgroundColor(Color.parseColor("#03A9F4"));
                            ButtonAnswer3.setBackgroundColor(Color.parseColor("#03A9F4"));
                            ButtonAnswer4.setBackgroundColor(Color.parseColor("#03A9F4"));
                            updateQuestion();
                        }
                    }, 1500);




                }
            }
        });


        ButtonAnswer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ButtonAnswer2.getText().toString().equals(ans.get(tcount)))
                {
                    correct++;
                    Toast.makeText(getApplicationContext(),"Correct answer",Toast.LENGTH_SHORT).show();
                    ButtonAnswer2.setBackgroundColor(Color.GREEN);
                    Handler handler = new Handler();

                    handler.postDelayed(new Runnable() {
                        public void run() {
                            ButtonAnswer2.setBackgroundColor(Color.parseColor("#03A9F4"));
                            updateQuestion();
                        }
                    }, 2000);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Incorrect",Toast.LENGTH_SHORT).show();
                    incorrect = incorrect + 1;

                    ButtonAnswer2.setBackgroundColor(Color.RED);

                    if(ButtonAnswer1.getText().toString().equals(ans.get(tcount)))
                    {
                        ButtonAnswer1.setBackgroundColor(Color.GREEN);
                    }
                    else if(ButtonAnswer3.getText().toString().equals(ans.get(tcount)))
                    {
                        ButtonAnswer3.setBackgroundColor(Color.GREEN);
                    }
                    else if(ButtonAnswer4.getText().toString().equals(ans.get(tcount)))
                    {
                        ButtonAnswer4.setBackgroundColor(Color.GREEN);
                    }



                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            ButtonAnswer1.setBackgroundColor(Color.parseColor("#03A9F4"));
                            ButtonAnswer2.setBackgroundColor(Color.parseColor("#03A9F4"));
                            ButtonAnswer3.setBackgroundColor(Color.parseColor("#03A9F4"));
                            ButtonAnswer4.setBackgroundColor(Color.parseColor("#03A9F4"));
                            updateQuestion();
                        }
                    }, 1500);

                }
            }
        });


        ButtonAnswer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ButtonAnswer3.getText().toString().equals(ans.get(tcount)))
                {
                    correct++;
                    Toast.makeText(getApplicationContext(),"Correct answer",Toast.LENGTH_SHORT).show();

                    ButtonAnswer3.setBackgroundColor(Color.GREEN);
                    Handler handler = new Handler();

                    handler.postDelayed(new Runnable() {
                        public void run() {
                            ButtonAnswer3.setBackgroundColor(Color.parseColor("#03A9F4"));
                            updateQuestion();
                        }
                    }, 2000);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Incorrect",Toast.LENGTH_SHORT).show();
                    incorrect = incorrect+1;

                    ButtonAnswer3.setBackgroundColor(Color.RED);



                    if(ButtonAnswer1.getText().toString().equals(ans.get(tcount)))
                    {
                        ButtonAnswer1.setBackgroundColor(Color.GREEN);
                    }
                    else if(ButtonAnswer2.getText().toString().equals(ans.get(tcount)))
                    {
                        ButtonAnswer2.setBackgroundColor(Color.GREEN);
                    }
                    else if(ButtonAnswer4.getText().toString().equals(ans.get(tcount)))
                    {
                        ButtonAnswer4.setBackgroundColor(Color.GREEN);
                    }




                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            ButtonAnswer1.setBackgroundColor(Color.parseColor("#03A9F4"));
                            ButtonAnswer2.setBackgroundColor(Color.parseColor("#03A9F4"));
                            ButtonAnswer3.setBackgroundColor(Color.parseColor("#03A9F4"));
                            ButtonAnswer4.setBackgroundColor(Color.parseColor("#03A9F4"));

                            updateQuestion();
                        }
                    }, 1500);
                }
            }
        });

        ButtonAnswer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ButtonAnswer4.getText().toString().equals(ans.get(tcount)))
                {
                    correct++;
                    Toast.makeText(getApplicationContext(),"Correct answer",Toast.LENGTH_SHORT).show();
                    ButtonAnswer4.setBackgroundColor(Color.GREEN);
                    Handler handler = new Handler();

                    handler.postDelayed(new Runnable() {
                        public void run() {
                            ButtonAnswer4.setBackgroundColor(Color.parseColor("#03A9F4"));
                            updateQuestion();
                        }
                    }, 2000);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Incorrect",Toast.LENGTH_SHORT).show();
                    incorrect = incorrect + 1;

                    ButtonAnswer4.setBackgroundColor(Color.RED);

                    if(ButtonAnswer1.getText().toString().equals(ans.get(tcount)))
                    {
                        ButtonAnswer1.setBackgroundColor(Color.GREEN);
                    }
                    else if(ButtonAnswer2.getText().toString().equals(ans.get(tcount)))
                    {
                        ButtonAnswer2.setBackgroundColor(Color.GREEN);
                    }
                    else if(ButtonAnswer3.getText().toString().equals(ans.get(tcount)))
                    {
                        ButtonAnswer3.setBackgroundColor(Color.GREEN);
                    }


                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            ButtonAnswer1.setBackgroundColor(Color.parseColor("#03A9F4"));
                            ButtonAnswer2.setBackgroundColor(Color.parseColor("#03A9F4"));
                            ButtonAnswer3.setBackgroundColor(Color.parseColor("#03A9F4"));
                            ButtonAnswer4.setBackgroundColor(Color.parseColor("#03A9F4"));
                            updateQuestion();
                        }
                    }, 1500);
                }
            }
        });




    }

    private void updateQuestion() {

        computerCount++;


        if(computerCount>=15)
        {
            Intent i = new Intent(InterMediateQuizActivity.this,ResultActivity.class);
            i.putExtra("total",String.valueOf(total));
            i.putExtra("correct",String.valueOf(correct));
            i.putExtra("incorrect",String.valueOf(incorrect));
            startActivity(i);

        }
        else
        {
            total++;

            tcount = tcount+1;

            tvquestion.setText(ques.get(tcount));
            ButtonAnswer1.setText(ans1.get(tcount));
            ButtonAnswer2.setText(ans2.get(tcount));
            ButtonAnswer3.setText(ans3.get(tcount));
            ButtonAnswer4.setText(ans4.get(tcount));
        }

    }

    public void reverseTimer(int seconds,final TextView tv){

        new CountDownTimer(seconds* 1000+1000, 1000) {

            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000);
                int minutes = seconds / 60;
                seconds = seconds % 60;
                timer.setText(String.format("%02d", minutes)
                        + ":" + String.format("%02d", seconds));
            }

            public void onFinish() {
                tv.setText("Completed");
                Intent i = new Intent(InterMediateQuizActivity.this,ResultActivity.class);
                i.putExtra("total",String.valueOf(total));
                i.putExtra("correct",String.valueOf(correct));
                i.putExtra("incorrect",String.valueOf(incorrect));
                startActivity(i);
            }
        }.start();




    }
}