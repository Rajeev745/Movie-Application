package com.example.moviesapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moviesapp.fragment.HomeBottomFragment
import com.example.moviesapp.fragment.RegisterFragment

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, RegisterFragment()).commit()
    }
}