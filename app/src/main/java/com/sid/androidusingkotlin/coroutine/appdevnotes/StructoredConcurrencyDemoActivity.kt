package com.sid.androidusingkotlin.coroutine.appdevnotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sid.androidusingkotlin.R
import kotlinx.android.synthetic.main.activity_structored_concurrency_demo.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

class StructoredConcurrencyDemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_structored_concurrency_demo)

        btnDownloadUserData.setOnClickListener(){
            CoroutineScope(Main).launch {
                tvUserMessage.text = UserDataManager2().getTotalUserCount().toString()
            }
        }
    }
}