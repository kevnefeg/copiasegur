package com.rosales.adoptame.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rosales.adoptame.repository.LoginRepository
import com.rosales.adoptame.repository.PetCardRepository
import com.rosales.adoptame.repository.RegisterRepository
import com.rosales.adoptame.ui.login.LoginViewModel
import com.rosales.adoptame.ui.petcard.PetCardViewModel
import com.rosales.adoptame.ui.register.RegisterViewModel

class ViewModelFactory<R>(private val repository: R):
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel(repository as RegisterRepository) as T
        }
        if (modelClass.isAssignableFrom(PetCardViewModel::class.java)) {
            return PetCardViewModel(repository as PetCardRepository) as T
        }
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(repository as LoginRepository) as T
        }
        throw IllegalArgumentException("Unknown AddViewModel Class")
    }
}