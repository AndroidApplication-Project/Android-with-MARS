package Quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class AdvancedQuizActivity extends AppCompatActivity {

    Button ButtonAnswer1, ButtonAnswer2, ButtonAnswer3, ButtonAnswer4,endQuiz;
    TextView tvquestion, timer;
    int total = 0;
    int correct = 0;
    int incorrect = 0;
    DatabaseReference reference;
    int computerCount=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_quiz);
        reverseTimer(90,timer);
        updateQuestion();


        timer = (TextView) findViewById(R.id.timer);
        tvquestion = (TextView) findViewById(R.id.tvquestion);

        ButtonAnswer1 = (Button) findViewById(R.id.btnanswer1);
        ButtonAnswer2 = (Button) findViewById(R.id.btnanswer2);
        ButtonAnswer3 = (Button) findViewById(R.id.btnanswer3);
        ButtonAnswer4 = (Button) findViewById(R.id.btnanswer4);
        endQuiz  = (Button) findViewById(R.id.endQuiz);
        endQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Quiz Ended",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AdvancedQuizActivity.this,ResultActivity.class);
                intent.putExtra("total",String.valueOf(total));
                intent.putExtra("correct",String.valueOf(correct));
                intent.putExtra("incorrect",String.valueOf(incorrect));
                startActivity(intent);
            }
        });


    }
    private void updateQuestion() {
        computerCount++;


        if(computerCount>5)
        {
            Intent i = new Intent(AdvancedQuizActivity.this,ResultActivity.class);
            i.putExtra("total",String.valueOf(total));
            i.putExtra("correct",String.valueOf(correct));
            i.putExtra("incorrect",String.valueOf(incorrect));
            startActivity(i);

        }
        else
        {
            reference = FirebaseDatabase.getInstance().getReference().child("Questions/Advanced").child(String.valueOf(computerCount));
            total++;
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Question question = dataSnapshot.getValue(Question.class);
                    tvquestion.setText(question.getQuestion());
                    ButtonAnswer1.setText(question.getAnswer1());
                    ButtonAnswer2.setText(question.getAnswer2());
                    ButtonAnswer3.setText(question.getAnswer3());
                    ButtonAnswer4.setText(question.getAnswer4());


                    ButtonAnswer1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(ButtonAnswer1.getText().toString().equals(question.getAnswer()))
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
                                incorrect = incorrect+1;

                                ButtonAnswer1.setBackgroundColor(Color.RED);

                                if(ButtonAnswer2.getText().toString().equals(question.getAnswer()))
                                {
                                    ButtonAnswer2.setBackgroundColor(Color.GREEN);
                                }
                                else if(ButtonAnswer3.getText().toString().equals(question.getAnswer()))
                                {
                                    ButtonAnswer3.setBackgroundColor(Color.GREEN);
                                }
                                else if(ButtonAnswer4.getText().toString().equals(question.getAnswer()))
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
                            if(ButtonAnswer2.getText().toString().equals(question.getAnswer()))
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
                                incorrect= incorrect+1;

                                ButtonAnswer2.setBackgroundColor(Color.RED);

                                if(ButtonAnswer1.getText().toString().equals(question.getAnswer()))
                                {
                                    ButtonAnswer1.setBackgroundColor(Color.GREEN);
                                }
                                else if(ButtonAnswer3.getText().toString().equals(question.getAnswer()))
                                {
                                    ButtonAnswer3.setBackgroundColor(Color.GREEN);
                                }
                                else if(ButtonAnswer4.getText().toString().equals(question.getAnswer()))
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
                            if(ButtonAnswer3.getText().toString().equals(question.getAnswer()))
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



                                if(ButtonAnswer1.getText().toString().equals(question.getAnswer()))
                                {
                                    ButtonAnswer1.setBackgroundColor(Color.GREEN);
                                }
                                else if(ButtonAnswer2.getText().toString().equals(question.getAnswer()))
                                {
                                    ButtonAnswer2.setBackgroundColor(Color.GREEN);
                                }
                                else if(ButtonAnswer4.getText().toString().equals(question.getAnswer()))
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
                            if(ButtonAnswer4.getText().toString().equals(question.getAnswer()))
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
                                incorrect = incorrect+1;

                                ButtonAnswer4.setBackgroundColor(Color.RED);

                                if(ButtonAnswer1.getText().toString().equals(question.getAnswer()))
                                {
                                    ButtonAnswer1.setBackgroundColor(Color.GREEN);
                                }
                                else if(ButtonAnswer2.getText().toString().equals(question.getAnswer()))
                                {
                                    ButtonAnswer2.setBackgroundColor(Color.GREEN);
                                }
                                else if(ButtonAnswer3.getText().toString().equals(question.getAnswer()))
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

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
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
                Intent i = new Intent(AdvancedQuizActivity.this,ResultActivity.class);
                i.putExtra("total",String.valueOf(total));
                i.putExtra("correct",String.valueOf(correct));
                i.putExtra("incorrect",String.valueOf(incorrect));
                startActivity(i);
            }
        }.start();

    }
}