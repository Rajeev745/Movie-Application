package com.example.moviesapp.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviesapp.repository.RegisterRepository

class LoginViewModelFactory(
    private val registerRepository: RegisterRepository,
    private val application: Application
) : ViewModelProvider.Factory {

    @Suppress("Unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(registerRepository, application) as T
        } else {
            throw IllegalArgumentException("Unknown View Model Class")
        }
    }
}