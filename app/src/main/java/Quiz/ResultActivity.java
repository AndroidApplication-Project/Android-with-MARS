package Quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.androidwithmars.R;

public class ResultActivity extends AppCompatActivity {
    TextView t1_total,t2_correct,t3_incorrect;
    String total,correct,incorrect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        t2_correct = (TextView)findViewById(R.id.correct);
        t3_incorrect = (TextView)findViewById(R.id.incorrect);
        t1_total = (TextView)findViewById(R.id.attempted);
        Intent i = getIntent();
        total = i.getStringExtra("total");
        correct = i.getStringExtra("correct");
        incorrect = i.getStringExtra("incorrect");

        setValues();
    }

    private void setValues()
    {
        t1_total.setText(total);
        t2_correct.setText(correct);
        t3_incorrect.setText(incorrect);
    }

}
