package com.hooloovoochimico.meno

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.VISIBILITY_PUBLIC
import androidx.core.app.NotificationManagerCompat
import com.hooloovoochimico.meno.MenoConstant.EXTRA_MODIFY_ID
import com.hooloovoochimico.meno.MenoConstant.MENO_CHANNEL_ID
import com.hooloovoochimico.meno.MenoConstant.MENO_MODIFY_ACTION

class Notifier(private val context: Context, private val notificationId: Int, private val memo: Memo){
    private var mNotifyBuilder: NotificationCompat.Builder? = null

    init {
        createNotificationChannel()
        setNotify()
    }

    fun showNotify(){
        with(NotificationManagerCompat.from(context)) {
            // notificationId is a unique int for each notification that you must define
            notify(notificationId, mNotifyBuilder!!.build())
        }
    }

    private fun setNotify(){
        val modifyIntent = Intent(context, ModifyBroadcastReceiver::class.java).apply {
            action = MENO_MODIFY_ACTION
            putExtra(EXTRA_MODIFY_ID, notificationId)
        }
        val modifyPendingIntent = PendingIntent.getBroadcast(context, 0, modifyIntent, 0)

        val bodySub = try{
            "${memo.body.substring(0..20)}..."
        }catch(e: Exception){
            memo.body
        }

        mNotifyBuilder = NotificationCompat.Builder(context, MENO_CHANNEL_ID).apply {
            setSmallIcon(R.drawable.ic_launcher_background)
            setContentTitle(memo.title)
            setContentText(bodySub)
            setStyle(NotificationCompat.BigTextStyle().bigText(memo.body))
            priority = NotificationCompat.PRIORITY_DEFAULT
            setVisibility(VISIBILITY_PUBLIC)
            addAction(R.drawable.common_google_signin_btn_icon_dark,
                context.getString(R.string.modify_text),
                modifyPendingIntent)
        }
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "meno notification channel"
            val descriptionText = "this is the meno notification channel name"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(MENO_CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                    context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}