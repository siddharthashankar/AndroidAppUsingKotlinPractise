package com.sid.androidusingkotlin.coroutine.appdevnotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.sid.androidusingkotlin.R
import kotlinx.android.synthetic.main.activity_job_demo.*
import kotlinx.android.synthetic.main.activity_job_demo.textView
import kotlinx.android.synthetic.main.activity_notification_view.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO

class JobDemoActivity : AppCompatActivity() {
    lateinit var job1 : Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_demo)

        job1 = CoroutineScope(Dispatchers.Main).launch {
            downloadData()
        }

        cancelButton.setOnClickListener{
            job1.cancel()
            Log.i("MyTag", "job is cancelled...")
        }

        statusButton.setOnClickListener(){
            if (job1.isActive){
                textView.text = "Active"
            }else if (job1.isCancelled){
                textView.text = "Cancelled"
            }else if (job1.isCompleted){
                textView.text = "Completed"
            }else
                textView.text = "Unknown"
        }

    }

    private suspend fun downloadData() {
        withContext(IO){
            repeat(30){
                delay(1000)
                Log.i("MyTag", "repeating $it")
            }
        }
    }


}