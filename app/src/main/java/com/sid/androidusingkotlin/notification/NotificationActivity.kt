package com.sid.androidusingkotlin.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sid.androidusingkotlin.R
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlinx.android.synthetic.main.activity_notification.*
import java.util.*

class NotificationActivity : AppCompatActivity() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_notification)

            // Create and register notification channel api 26+
            val channelId = "My_Channel_ID"
            createNotificationChannel(channelId)
            val buttonIntent = Intent(this, MyBroadcastReceiver::class.java)
            buttonIntent.apply {
                action = "Do Pending Task"
                putExtra("My Favorite Color", "Blue Color")
            }

            val buttonPendingIntent = PendingIntent.getBroadcast(this, 0, buttonIntent, 0)


            btn.setOnClickListener {
                val notificationBuilder = NotificationCompat.Builder(this, channelId)
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setContentTitle("Notification Title")
                    .setContentText("Notification Message by Sid" )
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setContentIntent(buttonPendingIntent)
                    .addAction(R.drawable.ic_launcher_foreground, "Do Task", buttonPendingIntent)

                with(NotificationManagerCompat.from(this)) {
                    notify(1, notificationBuilder.build())
                }
            }
        }


        private fun createNotificationChannel(channelId: String) {
            // Create the NotificationChannel, but only on API 26+ (Android 8.0) because
            // the NotificationChannel class is new and not in the support library
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val name = "My Channel"
                val channelDescription = "Channel Description"
                val importance = NotificationManager.IMPORTANCE_DEFAULT

                val channel = NotificationChannel(channelId, name, importance)
                channel.apply {
                    description = channelDescription
                }

                // Finally register the channel with system

                val notificationManager =
                    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.createNotificationChannel(channel)
            }
        }

}