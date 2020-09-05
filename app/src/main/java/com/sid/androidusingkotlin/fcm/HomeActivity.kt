package com.sid.androidusingkotlin.fcm

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.sid.androidusingkotlin.R

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val intent = intent

        val title = intent.getStringExtra("fcm_title")
        val message = intent.getStringExtra("fcm_msg")

        if (!message.isNullOrEmpty()){
            AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", DialogInterface.OnClickListener{dialog, which ->}).show()
        }
    }
}