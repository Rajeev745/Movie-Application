package com.example.moviesapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.moviesapp.repository.RegisterRepository

class LoginViewModel(private val registerRepository: RegisterRepository, application: Application) :
    AndroidViewModel(application) {

//    val users = registerRepository.users

    val inputEmail = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    fun login() {

    }

    fun signUP() {

    }

}