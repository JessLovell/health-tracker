package com.example.healthtracker;

import android.os.Handler;
import android.os.SystemClock;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//This code from: https://www.c-sharpcorner.com/article/creating-stop-watch-android-application-tutorial/
public class StopWatch extends AppCompatActivity {

    TextView timer ;
    Button start, reset;
    long MillisecondTime, StartTime, TimeBuff, UpdateTime = 0L ;
    Handler handler;
    int Hours, Seconds, Minutes, MilliSeconds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_watch);

        timer = (TextView)findViewById(R.id.tvTimer);
        start = (Button)findViewById(R.id.btStart);
        reset = (Button)findViewById(R.id.btReset);

        handler = new Handler() ;

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (start.getText() == "Start"){
                    StartTime = SystemClock.uptimeMillis();
                    handler.postDelayed(runnable, 0);
                    reset.setEnabled(false);
                    start.setText("Pause");
                } else {
                    TimeBuff += MillisecondTime;
                    handler.removeCallbacks(runnable);
                    reset.setEnabled(true);
                    start.setText("Start");
                }

            }
        });


        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MillisecondTime = 0L;
                StartTime = 0L;
                TimeBuff = 0L;
                UpdateTime = 0L;
                Hours = 0;
                Seconds = 0;
                Minutes = 0;
                MilliSeconds = 0;

                timer.setText("00.00:00.000");

            }
        });

    }

    public Runnable runnable = new Runnable() {

        public void run() {

            MillisecondTime = SystemClock.uptimeMillis() - StartTime;
            UpdateTime = TimeBuff + MillisecondTime;

            Seconds = (int) (UpdateTime / 1000);
            Minutes = Seconds / 60;
            Seconds = Seconds % 60;
            MilliSeconds = (int) (UpdateTime % 1000);

            timer.setText("" + String.format("%02d", Hours) + ":"
                    + String.format("%02d", Minutes) + ":"
                    + String.format("%02d", Seconds) + "."
                    + String.format("%03d", MilliSeconds));

            handler.postDelayed(this, 0);
        }

    };
}
