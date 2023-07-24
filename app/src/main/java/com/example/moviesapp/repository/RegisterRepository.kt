package com.example.moviesapp.repository

import android.util.Log
import com.example.moviesapp.database.RegisterDatabaseDao
import com.example.moviesapp.database.RegisterEntity

class RegisterRepository(private val dao: RegisterDatabaseDao) {
    private val TAG = "RegisterRepository"

    suspend fun getAllUsers(): List<RegisterEntity> {
        return dao.getAllUsers()
    }

    suspend fun insert(user: RegisterEntity) {
        dao.insert(user)
        Log.d(TAG, "Saved")
    }

    suspend fun getUserName(userName: String): RegisterEntity? {
        return dao.getUserName(userName)
    }

    suspend fun getEmailId(email: String): RegisterEntity? {
        return dao.getEmail(email)
    }
}