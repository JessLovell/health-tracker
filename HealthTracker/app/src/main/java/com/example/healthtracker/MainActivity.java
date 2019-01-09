package com.example.healthtracker;

import android.content.Intent;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    //FINGER EXERCISE
    private int click = 0;

    //CAROUSEL
    CarouselView carouselView;
    int[] sampleImages = {R.drawable.exercise, R.drawable.sleep, R.drawable.vegggies, R.drawable.water};
    String[] imageText = {"Exercise is important!",
            "Get Lots of sleep so you can be the most productive.",
            "Eat fruits and veggies to get more energy.", "Stay hydrated!" };


    //STOP WATCH
    TextView timer ;
    Button start, pause, reset;
    long MillisecondTime, StartTime, TimeBuff, UpdateTime = 0L ;
    Handler handler;
    int Seconds, Minutes, MilliSeconds ;

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


    public void fingerExercise(View v){

        TextView message = findViewById(R.id.textView2);
        this.click++;
        String text = "Get those fingers clicking! ";

        if (this.click % 50 == 0 && click != 0){
            text = "Look at those slim fingers! ";
        } else if (click % 10 == 0){
            text = "The fat is melting away! ";
        }
        message.setText(text + click);
    }

    public void stopWatchClicked(View v) {
        Intent stopWatch = new Intent(this, StopWatch.class);
        startActivity(stopWatch);
    }
}

