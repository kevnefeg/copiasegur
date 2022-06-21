package com.rosales.adoptame.ui.login

import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rosales.adoptame.network.ApiResponse
import com.rosales.adoptame.repository.LoginRepository
import com.rosales.adoptame.ui.register.RegisterActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: LoginRepository) : ViewModel() {
    val userField = MutableLiveData("")
    val passwordField = MutableLiveData("")
    private val _status = MutableLiveData<LoginUiStatus>(LoginUiStatus.Resume)
    val status: LiveData<LoginUiStatus>
        get() = _status

    fun onLogin(){
        _status.value = LoginUiStatus.Loading
        viewModelScope.launch(Dispatchers.IO) {
            _status.postValue(
                when(val response = repository.login(
                    userField.value.toString(),
                    passwordField.value.toString()
                )) {
                    is ApiResponse.Error -> LoginUiStatus.Error(response.exception)
                    is ApiResponse.ErrorWithMessage -> LoginUiStatus.Resume //TODO: Modificar el estado para permitir errores con mensajes
                    is ApiResponse.Success -> LoginUiStatus.Success(response.data)
                }
            )
        }
    }
}