package com.sid.androidusingkotlin.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sid.androidusingkotlin.Constant
import com.sid.androidusingkotlin.R
import com.sid.androidusingkotlin.showToast
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val bundle:Bundle? = intent.extras
        bundle?.let {
            val msg = bundle.getString(Constant.USER_MSG_KEY)
            tvResult.text = msg
            showToast("Received input "+msg)
        }

    }
}
