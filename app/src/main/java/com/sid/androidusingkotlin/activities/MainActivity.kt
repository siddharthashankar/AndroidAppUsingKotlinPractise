package com.sid.androidusingkotlin.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.sid.androidusingkotlin.Constant
import com.sid.androidusingkotlin.R
import com.sid.androidusingkotlin.coroutine.CoroutineActivity
import com.sid.androidusingkotlin.fcm.FCMActivity
import com.sid.androidusingkotlin.notification.NotificationActivity
import com.sid.androidusingkotlin.showToast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

companion object {
    val TAG:String = MainActivity::class.java.simpleName
}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSendMsg.setOnClickListener {
            val msg = editText.text.toString()
            Log.i(TAG,"input val $msg")
            showToast("input "+msg)
            var intent =  Intent(this, SecondActivity::class.java)
            intent.putExtra(Constant.USER_MSG_KEY,msg)
            startActivity(intent)
        }

        btnRecyclerView.setOnClickListener {
            var intent = Intent(this, HobbiesActivity::class.java)
            startActivity(intent)
        }

        btnNotification.setOnClickListener {
            var intent = Intent(this, NotificationActivity::class.java)
            startActivity(intent)
        }

        btnFCMNotification.setOnClickListener {
            startActivity(Intent(this, FCMActivity::class.java))
        }

        btnCoroutineWithMitch.setOnClickListener {
            startActivity(Intent(this, CoroutineActivity::class.java))
        }
    }
}
