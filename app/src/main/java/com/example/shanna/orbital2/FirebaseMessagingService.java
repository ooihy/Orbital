package com.example.shanna.orbital2;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.TaskStackBuilder;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.RemoteMessage;

import static android.app.Notification.EXTRA_NOTIFICATION_ID;

public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {

    private static final String CHANNEL_ID = "1";
    private DatabaseReference mDatabase;
    private FirebaseAuth auth;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        //Notification bar on the phone -> Will only be displayed if user is not using the app
        //and app is in the background.
        //For details of notification title and body, check out index.js
        String notification_title = remoteMessage.getNotification().getTitle(); //Title contains the project's title
        String notification_body = remoteMessage.getNotification().getBody();
        String click_action = remoteMessage.getNotification().getClickAction();

        /*
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.rocket_earth)
                .setContentTitle(notification_title)
                .setContentText(notification_body)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        */

        //Retrieve the user id of the sender of collab form
       // String senderId = remoteMessage.getData().get("from_user_id");
        String senderId = "22222";

        //Add a click action for the notification
        //Create an Intent for the activity you want to start
        Intent resultIntent = new Intent(click_action); //Go to AcceptRequest.class when user clicks on notifications
        resultIntent.putExtra("project_title", notification_title); //pass the project title to acceptRequest.java
        resultIntent.putExtra("Sender", "22222"); //pass the non-project owner's id to acceptRequest.java
        resultIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
       // Bundle extras = new Bundle();
       // extras.putString("project_title", notification_title);
       // extras.putString("Sender", senderId);


        /*
        // Create the TaskStackBuilder and add the intent, which inflates the back stack
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addNextIntentWithParentStack(resultIntent);
        // Get the PendingIntent containing the entire back stack
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        */
        //NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);
       // builder.setContentIntent(resultPendingIntent);

        //unique id each time
        int notificationId = (int)System.currentTimeMillis();
        PendingIntent resultPendingIntent = PendingIntent.getActivity(this,
                notificationId, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.rocket_earth)
                .setContentTitle(notification_title)
                .setContentText(notification_body)
                .setAutoCancel(true)
                .setContentIntent(resultPendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);


        //unique id each time
        //int notificationId = (int)System.currentTimeMillis();
        NotificationManagerCompat notificationManager1 = NotificationManagerCompat.from(this);
       // NotificationManager notificationManager1 = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // notificationId is a unique int for each notification that you must define
        notificationManager1.notify(0, mBuilder.build());


    }
}
