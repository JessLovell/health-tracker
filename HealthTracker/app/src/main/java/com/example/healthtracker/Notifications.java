package com.example.healthtracker;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Notifications extends AppCompatActivity {

    int notificationID = 0;
    private static final String CHANNEL_ID="channelid";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
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
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP + 3000, SystemClock.elapsedRealtime(), pendingIntent);

        System.out.println("sent");

//        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
//        notificationManager.notify(notificationID++, myBuilder.build());
    }
}
