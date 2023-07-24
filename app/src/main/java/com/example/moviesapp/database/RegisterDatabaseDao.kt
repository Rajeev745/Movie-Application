package com.example.moviesapp.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RegisterDatabaseDao {

    @Insert
    suspend fun insert(registerEntity: RegisterEntity)

    @Query("SELECT * FROM Register_users_table ORDER by userId DESC")
    suspend fun getAllUsers(): List<RegisterEntity>

    @Query("SELECT * FROM Register_users_table WHERE first_name LIKE :userName")
    suspend fun getUserName(userName: String): RegisterEntity

    @Query("SELECT * FROM Register_users_table WHERE email_id LIKE :email")
    suspend fun getEmail(email: String): RegisterEntity
}