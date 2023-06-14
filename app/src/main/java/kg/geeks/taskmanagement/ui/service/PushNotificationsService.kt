package kg.geeks.taskmanagement.ui.service

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.util.Log
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kg.geeks.taskmanagment.R

class PushNotificationsService : FirebaseMessagingService() {

    val channel = NotificationChannel(
        CHANNEL_ID,
        "Heads Up Notification",
        NotificationManager.IMPORTANCE_HIGH
    )

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        createNotification(message)
    }

    @SuppressLint("MissingPermission")
    private fun createNotification(message: RemoteMessage) {

        getSystemService(NotificationManager::class.java).createNotificationChannel(channel)

        val notification: Notification.Builder = Notification.Builder(this, CHANNEL_ID)
            .setContentTitle(message.notification?.title)
            .setContentText(message.notification?.body)
            .setSmallIcon(R.drawable.ic_google_btn)
            .setAutoCancel(false)

        NotificationManagerCompat.from(this).notify(1, notification.build())
    }

    companion object {
        const val CHANNEL_ID = "HEADS_UP_NOTIFICATION"
    }
}

/*  val channel = NotificationChannel(
        CHANNEL_ID, "TASK_55",
        NotificationManager.IMPORTANCE_HIGH
    )
c     Log.e("ololo", "onMessageReceived: " + message.notification?.title)
        Log.e("ololo", "onMessageReceived: " + message.notification?.body)


    override fun onMessageReceived(message: RemoteMessage) {
        createNotification(message)
        super.onMessageReceived(message)
    }

    private fun createNotification(message: RemoteMessage) {
        getSystemService(NotificationManager::class.java).createNotificationChannel(channel)

        val notification: Notification.Builder = Notification.Builder(this, CHANNEL_ID)
            .setContentTitle(message.notification?.title)
            .setContentText(message.notification?.body)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setAutoCancel(true)

           // NotificationManagerCompat.from(this).notify(1, notification.build())
    }

    companion object {
        const val CHANNEL_ID = "HEADS_UP_NOTIFICATION_55"
    }*/

