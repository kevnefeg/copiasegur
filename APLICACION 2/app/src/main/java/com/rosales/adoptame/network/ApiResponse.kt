package com.rosales.adoptame.network

sealed class ApiResponse<T> {
    data class Success<T>(val data: T) : ApiResponse<T>() //TODO: COMO HACER PARA VARIOS PARAMETROS
    data class Error<T>(val exception: Exception) : ApiResponse<T>()
    data class ErrorWithMessage<T>(val message: String) : ApiResponse<T>()
}