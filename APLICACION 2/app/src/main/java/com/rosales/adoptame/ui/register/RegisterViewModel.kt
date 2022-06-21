package com.rosales.adoptame.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rosales.adoptame.network.ApiResponse
import com.rosales.adoptame.repository.RegisterRepository
import com.rosales.adoptame.ui.login.LoginUiStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterViewModel (private val repository: RegisterRepository) : ViewModel() {
    val emailField = MutableLiveData("")
    val fullnameField = MutableLiveData("")
    val passwordField = MutableLiveData("")
    val phoneNumber = MutableLiveData("")
    val role = "user"
    private val _status = MutableLiveData<RegisterUiStatus>(RegisterUiStatus.Resume)
    val status: LiveData<RegisterUiStatus>
        get() = _status

    fun signUp(){
        _status.value = RegisterUiStatus.Loading //.loading
        viewModelScope.launch(Dispatchers.IO) {
            _status.postValue(
                when(val response = repository.register(
                    emailField.value.toString(),
                    fullnameField.value.toString(),
                    passwordField.value.toString(),
                    phoneNumber.value.toString(),
                    role
                )) {
                    is ApiResponse.Error -> RegisterUiStatus.Error(response.exception)
                    is ApiResponse.ErrorWithMessage -> RegisterUiStatus.Resume //TODO: Modificar el estado para permitir errores con mensajes
                    is ApiResponse.Success -> RegisterUiStatus.Success(response.data )
                }
            )
        }
    }
}
