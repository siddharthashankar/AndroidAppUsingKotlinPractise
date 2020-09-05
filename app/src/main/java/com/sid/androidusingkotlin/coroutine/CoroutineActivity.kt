package com.sid.androidusingkotlin.coroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sid.androidusingkotlin.R
import kotlinx.android.synthetic.main.activity_coroutine.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CoroutineActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine)

        btnClickMe.setOnClickListener {
            setNewText("Click!")

            CoroutineScope(IO).launch {
                fakeApiRequest()
            }
        }
    }

    private fun setNewText(input: String){
        val newText = text.text.toString()+ "\n$input"
        text.text = newText
    }

    private suspend fun setTextOnMainThread(input: String){
        withContext(Main){
            setNewText(input)
        }
    }

    private suspend fun fakeApiRequest(){
        logThread("fakeApiRequest")

        val result1 = getResult1FromApi()

        if (result1.equals("Result #1")){
            setTextOnMainThread("Got $result1")
            val result2 = getResult2FromApi()   // wait until job is done.
            if (result2.equals("Result #2")){
                setTextOnMainThread("Got $result2")
            }else {
                setTextOnMainThread("Couldn't get Result #2")
            }
        }else{
            setTextOnMainThread("Couldn't get Result #1")
        }
    }

    private suspend fun getResult1FromApi(): String{
        logThread("getResult1FromApi")
        delay(1000)
        return "Result #1"
    }

    private suspend fun getResult2FromApi(): String{
        logThread("getResult2FromApi")
        delay(1000)
        return "Result #2"
    }

    private fun logThread(methodName: String){
        println("debug: ${methodName}: ${Thread.currentThread().name}")
    }


}