package IntroSlider;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.androidwithmars.R;

public class SliderAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;
    public SliderAdapter(Context context){
        this.context = context;
    }

    public int[] slide_Images={
            R.drawable.img1,R.drawable.img2,R.drawable.img3

    };
    public String[] slide_Headings={
            "Android Experience",
            "Easy to Learn",
            "Exercises to practice"

    };
    public String[] slide_Description={
            "Want To Learn Android?? Start your Android Journey with US!!!!",
            "Videos and Documents for Easy Learning .",
            "Quiz to Test Your Knowledge."
    };
    @Override
    public int getCount() {
        return slide_Headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view ==(LinearLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater =(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);

        ImageView slideImageview = (ImageView)view.findViewById(R.id.firstSlide);
        TextView slideHeading = (TextView)view.findViewById(R.id.slide_heading);
        TextView slideDescription = (TextView)view.findViewById(R.id.slide_description);
        slideImageview.setImageResource(slide_Images[position]);
        slideHeading.setText(slide_Headings[position]);
        slideDescription.setText(slide_Description[position]);
        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout)object);
    }
}
