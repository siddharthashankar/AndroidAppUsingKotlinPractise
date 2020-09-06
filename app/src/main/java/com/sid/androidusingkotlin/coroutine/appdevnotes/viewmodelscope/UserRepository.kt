package com.sid.androidusingkotlin.coroutine.appdevnotes.viewmodelscope

import kotlinx.coroutines.delay

class UserRepository {
    suspend fun getUsers(): List<User>{
        delay(8000)
        val users: List<User> = listOf(
            User(1,"Sid"),
            User(2,"Kavi"),
            User(3, "Muk"),
            User(4, "Khu")
        )
        return users
    }
}