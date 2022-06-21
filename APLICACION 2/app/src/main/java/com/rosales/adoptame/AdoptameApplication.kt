package com.rosales.adoptame

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.rosales.adoptame.data.AdoptameDataBase
import com.rosales.adoptame.network.RetrofitInstance
import com.rosales.adoptame.repository.LoginRepository
import com.rosales.adoptame.repository.PetCardRepository
import com.rosales.adoptame.repository.RegisterRepository

class AdoptameApplication: Application() {
    private val prefs: SharedPreferences by lazy {
        getSharedPreferences("Adoptame", Context.MODE_PRIVATE)
    }

    private val dataBase by lazy{
        AdoptameDataBase.getInstance(this)
    }

    private fun getAPIService() = with(RetrofitInstance) {
        setToken(getToken())
        getAdoptameService()
    }

    fun getPetCardRepository() =
        PetCardRepository(dataBase,getAPIService())

    fun getLoginRepository() =
        LoginRepository(getAPIService())

    fun getRegisterRepository() =
        RegisterRepository(getAPIService())

    private fun getToken(): String = prefs.getString(USER_TOKEN,"")!!

    fun isUserLogin() = getToken() != ""

    fun saveAuthToken(token: String){
        val editor = prefs.edit()
        editor.putString(USER_TOKEN,token)
        editor.apply()
    }

    companion object{
        const val USER_TOKEN = "user_token"
    }
}