package com.rosales.adoptame.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.material.snackbar.Snackbar
import com.rosales.adoptame.AdoptameApplication
import com.rosales.adoptame.MainActivity
import com.rosales.adoptame.R
import com.rosales.adoptame.databinding.ActivityLoginBinding
import com.rosales.adoptame.ui.ViewModelFactory
import com.rosales.adoptame.ui.register.RegisterActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val app by lazy {
        application as AdoptameApplication
    }
    private  val viewModelFactory by lazy {
        ViewModelFactory(app.getLoginRepository())
    }
    private val viewModel: LoginViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (app.isUserLogin()){
            return startMainActivity()
        }
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.viewModel = viewModel

        viewModel.status.observe(this) {
                status -> handleUiState(status)
        }

        binding.signUp.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
    }


    private fun handleUiState(status: LoginUiStatus){
        when (status){
            is LoginUiStatus.Error -> Snackbar.make(binding.appLogo,"Ha sucedido un error " + status.exception.message, Snackbar.LENGTH_SHORT).show()
            LoginUiStatus.Loading -> Log.d("Login List Status","Loading")
            LoginUiStatus.Resume -> Log.d("Login List Status","Resume")
            is LoginUiStatus.Success -> {
                app.saveAuthToken(status.token)
                startMainActivity()
            }
        }
    }

    private fun startMainActivity(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }


}