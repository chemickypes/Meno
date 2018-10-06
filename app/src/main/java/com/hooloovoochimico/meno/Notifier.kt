package com.hooloovoochimico.meno

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class Notifier(private val context: Context, private val notificationId: Int, private val memo: Memo){
    private var mNotifyBuilder: NotificationCompat.Builder? = null

    companion object {
        private const val CHANNEL_ID = "99"
    }

    init {
        createNotificationChannel()
    }

    fun showNotify(){
        with(NotificationManagerCompat.from(context)) {
            // notificationId is a unique int for each notification that you must define
            notify(notificationId, mNotifyBuilder!!.build())
        }
    }

    fun setNotify(){
        mNotifyBuilder = NotificationCompat.Builder(context, CHANNEL_ID)
//                .setSmallIcon(R.drawable.notification_icon)
                .setContentTitle(memo.title)
                .setContentText(memo.body)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
    }

    fun setBigText(){
        mNotifyBuilder?.setStyle(NotificationCompat.BigTextStyle().bigText(memo.body))
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "meno notification channel"
            val descriptionText = "this is the meno notification channel name"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                    context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}