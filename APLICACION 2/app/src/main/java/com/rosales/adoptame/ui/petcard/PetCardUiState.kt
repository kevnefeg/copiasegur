package com.rosales.adoptame.ui.petcard

import androidx.lifecycle.LiveData
import com.rosales.adoptame.data.model.Pet

sealed class PetCardUiState {
    object Loading: PetCardUiState()
    class Error(val exception: Exception) : PetCardUiState()
    data class Success(val pet: LiveData<List<Pet>>) : PetCardUiState()
}