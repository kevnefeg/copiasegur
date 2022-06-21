package com.rosales.adoptame.network.dto

data class RegisterResponse (
    val fullname:String,
    val email:String,
    val phone:String,
    val password:String,
    val role:String
    )