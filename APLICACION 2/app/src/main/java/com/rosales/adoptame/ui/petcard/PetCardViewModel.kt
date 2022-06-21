package com.rosales.adoptame.ui.petcard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rosales.adoptame.network.ApiResponse
import com.rosales.adoptame.repository.PetCardRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


    class PetCardViewModel(private val repository: PetCardRepository): ViewModel() {

        //by default
        private val _status = MutableLiveData<PetCardUiState>(PetCardUiState.Loading)
        val status: LiveData<PetCardUiState>
            get() = _status

        fun getAllPets() {
            _status.value = PetCardUiState.Loading
            viewModelScope.launch(Dispatchers.IO) {
                _status.postValue(
                    when (val response = repository.getAllPetCards()){
                        is ApiResponse.Error -> PetCardUiState.Error(response.exception)
                        is ApiResponse.Success -> PetCardUiState.Success(response.data)
                        is ApiResponse.ErrorWithMessage -> TODO()
                    }
                )
            }
        }
    }

