package com.rosales.adoptame.ui.register

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.material.snackbar.Snackbar
import com.rosales.adoptame.AdoptameApplication
import com.rosales.adoptame.R
import com.rosales.adoptame.databinding.ActivityRegisterBinding
import com.rosales.adoptame.ui.ViewModelFactory
import com.rosales.adoptame.ui.login.LoginActivity

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private val app by lazy {
        application as AdoptameApplication
    }
    private  val viewModelFactory by lazy {
        ViewModelFactory(app.getRegisterRepository())
    }
    private val viewModel: RegisterViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        binding.viewModel = viewModel

        viewModel.status.observe(this) {
                status -> handleUiState(status)
        }
        binding.buttonSignUp.setOnClickListener {
            viewModel.signUp()
            startLoginView()
        }
    }

    private fun handleUiState(status: RegisterUiStatus){
        when (status){
            is RegisterUiStatus.Error -> Snackbar.make(binding.appLogo,"Ha sucedido un error " + status.exception.message, Snackbar.LENGTH_SHORT).show()
            RegisterUiStatus.Loading -> Log.d("Login List Status","Loading")
            RegisterUiStatus.Resume -> Log.d("Login List Status","Resume")
            is RegisterUiStatus.Success -> {
                startLoginView()
            }
        }
    }

    private fun startLoginView(){
        val intent = Intent(this, LoginActivity::class.java)
        println("Llego hasta startlogin")
        startActivity(intent)
        finish()

    }
}