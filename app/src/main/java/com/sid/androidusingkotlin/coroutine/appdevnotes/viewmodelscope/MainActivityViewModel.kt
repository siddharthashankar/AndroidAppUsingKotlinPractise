package com.sid.androidusingkotlin.coroutine.appdevnotes.viewmodelscope

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivityViewModel : ViewModel() {
    private var userRepository = UserRepository()
    // way1
   /* var users : MutableLiveData<List<User>> = MutableLiveData()

    fun getUserData(){
        viewModelScope.launch {
            var result: List<User>? = null
            withContext(IO){
                result = userRepository.getUsers()
            }
            users.value = result
        }
    }*/

    var users = liveData(IO){
        val result = userRepository.getUsers()
        emit(result)
    }
}