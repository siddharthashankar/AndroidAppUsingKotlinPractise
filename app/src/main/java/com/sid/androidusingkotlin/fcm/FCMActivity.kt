package com.sid.androidusingkotlin.fcm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.LocaleList
import androidx.appcompat.app.AlertDialog
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.sid.androidusingkotlin.R
import com.sid.androidusingkotlin.fcm.MyFirebaseMessagingService.Companion.INTENT_ACTION_SEND_MESSAGE
import java.util.*

class FCMActivity : AppCompatActivity() {

    lateinit var receiver: BroadcastReceiver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fcm)

        receiver = object : BroadcastReceiver(){
            override fun onReceive(context: Context?, intent: Intent?) {
                val fcmMsg = intent?.getStringExtra("message")

                AlertDialog
                    .Builder(this@FCMActivity)
                    .setMessage(fcmMsg)
                    .setTitle("UOB FCM Title")
                    .setPositiveButton("Ok"){_ , _ -> }
                    .create().show()
            }

        }
    }

    override fun onResume() {
        super.onResume()
        val filter = IntentFilter(INTENT_ACTION_SEND_MESSAGE)
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, filter)
    }

    override fun onPause() {
        super.onPause()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver)
    }


}