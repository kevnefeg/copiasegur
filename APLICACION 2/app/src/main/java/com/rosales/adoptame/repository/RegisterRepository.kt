package com.rosales.adoptame.repository

import android.media.Image
import com.rosales.adoptame.data.model.User
import com.rosales.adoptame.network.AdoptameService
import com.rosales.adoptame.network.ApiResponse
import com.rosales.adoptame.network.dto.RegisterRequest
import com.rosales.adoptame.network.dto.RegisterResponse
import retrofit2.HttpException
import java.io.IOException

class RegisterRepository(private val api: AdoptameService) {
    suspend fun register(
        email:String,
        fullname: String,
        password: String,
        phone: String,
        role: String
    ): ApiResponse<User> {
        try {
            val response = api.register(RegisterRequest(email,fullname, password, phone, role))
            val user = User(
                response.email,
                response.fullname,
                response.password,
                response.phone,
                response.role,

            )
            return ApiResponse.Success(
                user
            )
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