package com.example.healthtracker;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;


public class MainActivity extends AppCompatActivity {

    //FINGER EXERCISE
    private int click = 0;

    //CAROUSEL
    CarouselView carouselView;
    int[] sampleImages = {R.drawable.exercise, R.drawable.sleep, R.drawable.vegggies, R.drawable.water};
    String[] imageText = {"Exercise is important!",
            "Get Lots of sleep so you can be the most productive.",
            "Eat fruits and veggies to get more energy.", "Stay hydrated!" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //for the carousel
        carouselView = (CarouselView) findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(imageListener);
    }

    //directions from carousel Library
    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            TextView textView = (TextView) findViewById(R.id.textView);
            textView.setText(imageText[position]);

            imageView.setImageResource(sampleImages[position]);
        }
    };

    public void stopWatchClicked(View v) {
        Intent stopWatch = new Intent(this, StopWatch.class);
        startActivity(stopWatch);
    }

    public void onFingerExerciseClick(View v) {
        Intent fingerExerciseIntent = new Intent(this, FingerExercises.class);
        startActivity(fingerExerciseIntent);
    }
}

