package com.example.uberfood.utils;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.uberfood.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.HashMap;
import java.util.Map;

import static com.example.uberfood.utils.Constants.USER_COLLECTION;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static int count = 0;

    @Override
    public void onNewToken(String token) {
        super.onNewToken(token);
        sendRegistrationToServer(token);

    }

    private void sendRegistrationToServer(String token) {


        HashMap<String, Object> tokenToFirebase = new HashMap<>();
        tokenToFirebase.put("token" , token);
        FirebaseFirestore.getInstance().collection(USER_COLLECTION)
                // add this field to your application and set up the firebase application to get only the new token " don't miss to reload the token after each update
               //




      // sen dtoken to backend and add this to lis t


    }


    @Override
    public void onDeletedMessages() {
        super.onDeletedMessages();
    }


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        //Displaying data in log
        //It is optional



        Log.d("", "Notification Message TITLE: " + remoteMessage.getNotification().getTitle());
        Log.d("", "Notification Message BODY: " + remoteMessage.getNotification().getBody());
        Log.d("", "Notification Message DATA: " + remoteMessage.getData().toString());

//Calling method to generate notification
        sendNotification(remoteMessage.getNotification().getTitle(),
                remoteMessage.getNotification().getBody(), remoteMessage.getData());
    }


    //This method is only generating push notification
    private void sendNotification(String messageTitle, String messageBody, Map<String, String> row) {
        PendingIntent contentIntent = null;
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.restaurant))
                .setSmallIcon(R.drawable.restaurant)
                .setContentTitle(messageTitle)
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(contentIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(count, notificationBuilder.build());
        count++;

    }
}

