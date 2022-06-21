package com.rosales.adoptame.ui.register

import com.rosales.adoptame.data.model.User

sealed class RegisterUiStatus {
    object Resume : RegisterUiStatus()
    object Loading : RegisterUiStatus()
    class Error(val exception: Exception) : RegisterUiStatus()
    data class Success(
        val user: User
        ) : RegisterUiStatus()
}