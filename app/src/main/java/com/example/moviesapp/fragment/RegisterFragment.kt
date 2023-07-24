package com.example.moviesapp.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.moviesapp.database.RegisterDatabase
import com.example.moviesapp.database.RegisterDatabaseDao
import com.example.moviesapp.databinding.FragmentRegisterBinding
import com.example.moviesapp.repository.RegisterRepository
import com.example.moviesapp.viewmodel.RegisterViewModel
import com.example.moviesapp.viewmodel.RegisterViewModelFactory

class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var dao: RegisterDatabaseDao
    private lateinit var viewModel: RegisterViewModel
    private lateinit var repository: RegisterRepository
    private lateinit var factory: RegisterViewModelFactory
    private val TAG = "RegisterFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val application = requireNotNull(this.activity).application
        dao = RegisterDatabase.getInstance(application).registerDatabaseDao
        repository = RegisterRepository(dao)
        factory = RegisterViewModelFactory(repository, application, requireContext())
        viewModel = ViewModelProvider(this, factory).get(RegisterViewModel::class.java)
        binding.registerUser.setOnClickListener {
            registerUser()
        }
        observerChanges()
    }

    private fun observerChanges() {
        viewModel.getUserName("rajeev").observe(viewLifecycleOwner, Observer {
            Log.d(TAG, it.firstName)
        })
        viewModel.getAllUsers().observe(viewLifecycleOwner, Observer {
            Log.d(TAG, it.toString())
        })
    }

    private fun registerUser() {
        val firstName = binding.userName.text.toString()
        val lastName = binding.lastName.text.toString()
        val email = binding.email.text.toString()
        val password = binding.password.text.toString()

        if (firstName == "" || lastName == "" || email == "" || password == "") {
            Toast.makeText(context, "Please enter all fields", Toast.LENGTH_SHORT).show()
        } else {
            viewModel.setUserData(firstName, lastName, email, password)
        }
        Log.d(TAG, "registration completed")
    }

}