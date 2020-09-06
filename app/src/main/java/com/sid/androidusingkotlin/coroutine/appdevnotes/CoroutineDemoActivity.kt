package com.sid.androidusingkotlin.coroutine.appdevnotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.sid.androidusingkotlin.R
import kotlinx.android.synthetic.main.activity_coroutine_demo.*
import kotlinx.coroutines.*

class CoroutineDemoActivity : AppCompatActivity() {
    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine_demo)

        btnCount.setOnClickListener {
            tvCount.text = count++.toString()
        }
        btnDownloadUserData.setOnClickListener {

            CoroutineScope(Dispatchers.IO).launch {
                downloadUserData()
            }
        }
    }

    private suspend fun downloadUserData() {
        for (i in 1..200000) {
            withContext(Dispatchers.Main){
                tvUserMessage.text =  "Downloading user $i in ${Thread.currentThread().name}"
            }
                delay(1000)
        }
    }
}