package monti.com.firebasePetagram;

//import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
//import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.NotificationCompat.WearableExtender;
import android.view.Gravity;

/**
 * Created by Susana on 24/11/2016.
 */

public class NotificationService extends FirebaseMessagingService {
    public static final String TAG = "FIREBASE";
    public static final int NOTIFICATION_ID = 001;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // ...

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
       Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
        //Intent i = new Intent(this, MainActivity.class);

        Uri sonido = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Intent i1 = new Intent();
        i1.setAction("VER_PERFIL");
        PendingIntent pendingIntent1 = PendingIntent.getBroadcast(this, 0, i1, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent i2 = new Intent();
        i2.setAction("SEGUIR");
        PendingIntent pendingIntent2 = PendingIntent.getBroadcast(this, 0, i2, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent i3 = new Intent();
        i3.setAction("VER_USUARIO");
        PendingIntent pendingIntent3 = PendingIntent.getBroadcast(this, 0, i3, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Action action1 =
                new NotificationCompat.Action.Builder(R.drawable.ic_full_perfil,
                        getString(R.string.texto_accion_perfil), pendingIntent1)
                        .build();

        NotificationCompat.Action action2 =
                new NotificationCompat.Action.Builder(R.drawable.ic_full_perfil,
                        getString(R.string.texto_seguir), pendingIntent2)
                        .build();

        NotificationCompat.Action action3 =
                new NotificationCompat.Action.Builder(R.drawable.ic_full_usuario,
                        getString(R.string.texto_seguir), pendingIntent2)
                        .build();


        NotificationCompat.WearableExtender wearableExtender =
                new NotificationCompat.WearableExtender()
                .setGravity(Gravity.CENTER_VERTICAL)
                ;

        NotificationCompat.Builder notificacion = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.notificacion)
                .setContentTitle("Notificacion")
                .setContentText(remoteMessage.getNotification().getBody())
                .setAutoCancel(true)
                .setSound(sonido)
                .setContentIntent(pendingIntent1)
                .extend(wearableExtender.addAction(action1))
                .extend(wearableExtender.addAction(action2))
                .extend(wearableExtender.addAction(action3))
                //.addAction(R.drawable.ic_full_perfil, getString(R.string.texto_accion_perfil),pendingIntent)

                ;

        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(this);
        notificationManager.notify(NOTIFICATION_ID, notificacion.build());
    }
}
