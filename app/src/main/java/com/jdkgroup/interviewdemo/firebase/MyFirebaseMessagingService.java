package com.jdkgroup.interviewdemo.firebase;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.text.Html;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.jdkgroup.interviewdemo.DrawerActivity;
import com.jdkgroup.interviewdemo.R;
import com.jdkgroup.utils.AppUtils;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private String title = "", icon = "";
    private Bitmap bitmap;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        title = remoteMessage.getNotification().getTitle();

       /* icon = remoteMessage.getNotification().getIcon();
        bitmap = getBitmapFromUrl(icon);
        remoteMessage.getData().get("type");
        String.valueOf(remoteMessage.getData().get("type_id"));*/

        sendNotification(String.valueOf(Html.fromHtml(remoteMessage.getNotification().getBody())));
    }

    private void sendNotification(String messageBody) {
        Intent intent = new Intent(this, DrawerActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, "")
                //.setLargeIcon(bitmap)
                .setDefaults(Notification.DEFAULT_ALL)
                .setContentTitle(title)
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setContentIntent(PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT))
                .setSmallIcon(R.mipmap.ic_launcher);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, notificationBuilder.build());
    }

  /*  public Bitmap getBitmapFromUrl(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(input);
            return bitmap;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;

        }
    }*/
}
