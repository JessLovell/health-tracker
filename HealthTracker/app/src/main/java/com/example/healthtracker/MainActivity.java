package com.example.healthtracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private int click = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

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
}

//    public void stopWatch(View v){
//
//        ImageButton playButton = findViewById(R.id.imageButton);
//
//        PrimeThread watch = new PrimeThread();
//        watch.start();
//
//        if (playButton.getContentDescription() == "Play"){
//            //start the stop watch.
//
//
//            //playButton.setContentDescription("Pause");
//            //change image to pause
////            playButton.setImageResource(R.drawable.c_media_pause);
//        } else if (playButton.getContentDescription() == "Pause"){
//            //Stop the watch
//
//            playButton.setContentDescription("Play");
//            //Change the image to play
//        }
//    }
//
//    public void resetWatch(View v){
//
//        ImageButton playButton = findViewById(R.id.imageButton);
//
//        if (playButton.getContentDescription() == "Pause"){
//            //reset the stop watch
//        }
//    }
//}

//class PrimeThread extends Thread {
//
//    int secondsPassed;
//    Timer timer;
//    TimerTask task = new TimerTask() {
//        @Override
//        public void run() {
//            secondsPassed++;
//            System.out.println("time: "+secondsPassed);
//        }
//    };
//
//    PrimeThread() {
//        this.timer = new Timer();
//    }
//
//    public void run() {
//        timer.scheduleAtFixedRate(task, 1000, 1000);
//    }
//
//}
