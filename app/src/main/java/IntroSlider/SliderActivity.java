package IntroSlider;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.androidwithmars.MainActivity;
import com.example.androidwithmars.MainFragment;
import com.example.androidwithmars.R;

public class SliderActivity extends AppCompatActivity {
    private ViewPager mSlideViewPager;
    private LinearLayout mDotsLayout;
    private SliderAdapter sliderAdapter;
    private TextView[] mDots;
    private Button nNextbutton;
    private Button nPreviousButton;
    private int mCurrentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider);
        mSlideViewPager = (ViewPager)findViewById(R.id.slideviewPager);
        mDotsLayout = (LinearLayout)findViewById(R.id.dotsLayout);
        nNextbutton=(Button)findViewById(R.id.nextButton);
        nPreviousButton=(Button)findViewById(R.id.previousButton);

        sliderAdapter = new SliderAdapter(this);
        mSlideViewPager.setAdapter(sliderAdapter);
        addDotsIndicator(0);
        mSlideViewPager.addOnPageChangeListener(viewListener);

        // onclickListeners
        nNextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSlideViewPager.setCurrentItem(mCurrentPage + 1);
            }
        });
        nPreviousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSlideViewPager.setCurrentItem(mCurrentPage -1);
            }
        });
    }
    // dots in bottom
    public void addDotsIndicator(int position){
        mDots = new TextView[3];
        mDotsLayout.removeAllViews();
        for (int i=0; i<mDots.length; i++){
            mDots[i]= new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(40);
            mDots[i].setTextColor(getResources().getColor(R.color.colorTranswhite));
            mDotsLayout.addView(mDots[i]);

        }
        if (mDots.length > 0){
            mDots[position].setTextColor(getResources().getColor(R.color.white));

        }
    }
    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int i) {
            addDotsIndicator(i);
            mCurrentPage = i;
            if (i == 0){
                nNextbutton.setEnabled(true);
                nNextbutton.setVisibility(View.VISIBLE);
                nPreviousButton.setEnabled(false);
                nPreviousButton.setVisibility(View.INVISIBLE);

                nNextbutton.setText("Next");
                nPreviousButton.setText("");
            }else if(i == mDots.length - 1){
                nNextbutton.setEnabled(true);
                nPreviousButton.setEnabled(true);
                nPreviousButton.setVisibility(View.VISIBLE);

                nNextbutton.setText("Home");
                nNextbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(SliderActivity.this, MainFragment.class);
                        startActivity(intent);
                    }
                });
                nPreviousButton.setText("Previous");
            }else {
                nNextbutton.setEnabled(true);
                nPreviousButton.setEnabled(true);
                nPreviousButton.setVisibility(View.VISIBLE);

                nNextbutton.setText("Next");
                nPreviousButton.setText("Previous");
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    }
