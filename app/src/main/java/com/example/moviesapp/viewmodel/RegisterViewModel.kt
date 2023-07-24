package com.example.moviesapp.viewmodel

import android.app.Application
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.content.contentValuesOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviesapp.HomeActivity
import com.example.moviesapp.database.RegisterEntity
import com.example.moviesapp.repository.RegisterRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val registerRepository: RegisterRepository,
    application: Application, private val context: Context
) : AndroidViewModel(application) {

    private var userLiveData = MutableLiveData<List<RegisterEntity>>()

    private var userDetail = MutableLiveData<RegisterEntity>()

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun getUserName(userName: String): MutableLiveData<RegisterEntity> {
        uiScope.launch {
            val user = registerRepository.getUserName(userName)
            userDetail.postValue(user)
        }

        return userDetail
    }

    fun getAllUsers(): MutableLiveData<List<RegisterEntity>> {
        uiScope.launch {
            val list = registerRepository.getAllUsers()
            userLiveData.postValue(list)
        }
        return userLiveData
    }

    fun setUserData(firstName: String, lastName: String, email: String, password: String) {
        val userData = RegisterEntity(0, firstName, lastName, email, password)
        uiScope.launch {
            val emailId = registerRepository.getEmailId(email)
            if(email == emailId?.emailId) {
                Toast.makeText(context, "Email Id already exists", Toast.LENGTH_SHORT).show()
            } else {
                registerRepository.insert(userData)
                val intent = Intent(context, HomeActivity::class.java)
                context.startActivity(intent)
            }
        }
    }
}