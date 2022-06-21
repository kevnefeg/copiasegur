package com.rosales.adoptame.network.dto

import android.media.Image

data class RegisterRequest (
    val email: String,
    val fullname: String,
    val password: String,
    val phone: String,
    val role: String
)