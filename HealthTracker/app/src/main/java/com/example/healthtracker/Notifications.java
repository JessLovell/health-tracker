package com.example.healthtracker;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.SystemClock;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Notifications extends AppCompatActivity {

    int notificationID = 0;
    private static final String CHANNEL_ID="channelId";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        //display user at top
        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.logged_in_user), Context.MODE_PRIVATE);
        String user = sharedPref.getString(getString(R.string.logged_in_user), "Welcome");
        TextView loggedInUser = findViewById(R.id.textView10);
        loggedInUser.setText("Welcome " + user);
    }


    public void sendNotification(View v){

        NotificationCompat.Builder myBuilder =
                new NotificationCompat.Builder(Notifications.this, CHANNEL_ID)
                .setSmallIcon(R.drawable.notification_icon)
                .setContentTitle("Health Tracker")
                .setContentText("Hydrate!")
                .setStyle(new NotificationCompat.BigTextStyle().bigText("Drink some water!"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        Intent intent = new Intent(Notifications.this, NotificationReciever.class);
            intent.putExtra("notification", myBuilder.build());
            intent.putExtra("notification_id", notificationID++);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(Notifications.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // schedule pending intent for happen later
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, SystemClock.elapsedRealtime(), 5000, pendingIntent);

        System.out.println("sent");
    }

}

