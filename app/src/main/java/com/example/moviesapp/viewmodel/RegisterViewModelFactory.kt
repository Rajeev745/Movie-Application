package com.example.moviesapp.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviesapp.repository.RegisterRepository

class RegisterViewModelFactory(
    private val registerRepository: RegisterRepository,
    private val application: Application,
    private val context: Context
) : ViewModelProvider.Factory {

    @Suppress("Unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel(registerRepository, application, context) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}