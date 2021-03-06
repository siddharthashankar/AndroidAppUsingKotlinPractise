package com.sid.androidusingkotlin.fcm

import android.content.Intent
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.firebase.installations.remote.TokenResult
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    private val TAG = "MyFirebaseMessagingServ"

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d(TAG, "Token-- "+token)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        Log.d(TAG, "FCM--"+remoteMessage.notification?.title+", "+remoteMessage.notification?.body)

        // Way1 :  direct pass FCM msg to Activity
/*        val intent = Intent(this@MyFirebaseMessagingService, HomeActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.putExtra("fcm_title", remoteMessage.notification?.title)
        intent.putExtra("fcm_msg", remoteMessage.notification?.body)
        startActivity(intent)*/

        // Way2:  Pass FCM msg to LocalBroadcastManager
        passMsgToActivity(remoteMessage.notification?.body)
    }

    private fun passMsgToActivity(fcmMsg: String?) {
        val intent = Intent().apply {
            action = INTENT_ACTION_SEND_MESSAGE
            putExtra("message", fcmMsg)
        }
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
    }

    companion object{
        const val INTENT_ACTION_SEND_MESSAGE = "INTENT_ACTION_SEND_MESSAGE"
    }

}