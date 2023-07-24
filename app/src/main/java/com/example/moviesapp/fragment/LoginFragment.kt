package com.example.moviesapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.moviesapp.database.RegisterDatabase
import com.example.moviesapp.database.RegisterDatabaseDao
import com.example.moviesapp.databinding.FragmentLoginBinding
import com.example.moviesapp.repository.RegisterRepository
import com.example.moviesapp.viewmodel.LoginViewModel
import com.example.moviesapp.viewmodel.LoginViewModelFactory

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var dao: RegisterDatabaseDao
    private lateinit var repository: RegisterRepository
    private lateinit var factory: LoginViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val application = requireNotNull(this.activity).application
        dao = RegisterDatabase.getInstance(application).registerDatabaseDao
        repository = RegisterRepository(dao)
        factory = LoginViewModelFactory(repository, application)
        loginViewModel = ViewModelProvider(this, factory).get(LoginViewModel::class.java)
    }
}