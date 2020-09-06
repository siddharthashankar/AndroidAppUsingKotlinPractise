package com.sid.androidusingkotlin.coroutine.appdevnotes.viewmodelscope

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sid.androidusingkotlin.R

class ViewModelScopeActivity : AppCompatActivity() {
    private lateinit var mainActivityViewModel: MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_model_scope)

        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
       // mainActivityViewModel.getUserData()
        mainActivityViewModel.users.observe(this, Observer { myUsers ->
            myUsers.forEach {
                Log.i("MyTag", "name is ${it.name}")
            }
        })
    }
}