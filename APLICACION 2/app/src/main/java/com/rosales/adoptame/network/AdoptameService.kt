package com.rosales.adoptame.network

import com.rosales.adoptame.network.dto.*
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AdoptameService {

    @POST("/login")
    suspend fun login(@Body credentials: LoginRequest): LoginResponse

    @POST("/register")
    suspend fun register(@Body credentials: RegisterRequest): RegisterResponse

    @GET("/")
    suspend fun getAllPet(): PetCardResponse

}