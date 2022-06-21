package com.rosales.adoptame.repository

import com.rosales.adoptame.network.AdoptameService
import com.rosales.adoptame.network.ApiResponse
import com.rosales.adoptame.network.dto.LoginRequest
import retrofit2.HttpException
import java.io.IOException

class LoginRepository(private val api: AdoptameService) {
    suspend fun login(email:String, password: String): ApiResponse<String> {
        try {
            val response = api.login(LoginRequest(email,password))
            return ApiResponse.Success(response.token)
        } catch (e: HttpException){
            if (e.code() == 400){
                //TODO: FALTA CONVERTIR EL BODY A UN OBJETO KOTLIN
                return ApiResponse.ErrorWithMessage(e.response()?.body().toString())
            }
            return ApiResponse.Error(e)
        } catch (e: IOException){
            return ApiResponse.Error(e)
        }
    }
}