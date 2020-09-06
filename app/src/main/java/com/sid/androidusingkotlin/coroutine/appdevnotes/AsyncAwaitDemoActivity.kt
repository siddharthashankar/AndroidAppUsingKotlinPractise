package com.sid.androidusingkotlin.coroutine.appdevnotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.sid.androidusingkotlin.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AsyncAwaitDemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_async_await_demo)
        // 1st Way, parallel decomposition
/*        CoroutineScope(IO).launch {
            var stock1 = async { getStock1() }
            var stock2 = async { getStock2() }
            val stock3 = async { getStock3()
            }
            var total = stock1.await() + stock2.await() + stock3.await()
            Log.i("MyTag", "Total is $total")
        }*/

        // 2nd Way, parallel decomposition
        CoroutineScope(Main).launch {
            Log.i("MyTag","Calculation Started...")
            val stock1 = async(IO) { getStock1() }
            val stock2 = async(IO) { getStock2() }
            val stock3 = async(IO) { getStock3() }
            val total = stock1.await()+stock2.await()+stock3.await()
            Toast.makeText(applicationContext, "Total = $total ", Toast.LENGTH_LONG).show()
        }
    }

    private suspend fun getStock1(): Int{
        delay(10000)
        Log.i("MyTag","stock 1 returned")
        return 55000
    }

    private suspend fun getStock2(): Int{
        delay(8000)
        Log.i("MyTag","stock 2 returned")
        return 35000
    }

    private suspend fun getStock3(): Int{
        delay(12000)
        Log.i("MyTag","stock 3 returned")
        return 15000
    }
}