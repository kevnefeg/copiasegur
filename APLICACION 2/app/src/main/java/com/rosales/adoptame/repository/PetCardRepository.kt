package com.rosales.adoptame.repository

import androidx.lifecycle.LiveData
import com.rosales.adoptame.data.AdoptameDataBase
import com.rosales.adoptame.network.AdoptameService
import com.rosales.adoptame.data.dao.PetDao
import com.rosales.adoptame.data.model.Pet
import com.rosales.adoptame.network.ApiResponse
import retrofit2.HttpException
import java.io.IOException


class PetCardRepository (
    database: AdoptameDataBase,
    private val api: AdoptameService
        ){
    private val petDoa = database.petDao()

    suspend fun getAllPetCards(): ApiResponse<LiveData<List<Pet>>> {
        //Try to get word from api
        return try {

            val response = api.getAllPet()
            println("Imprimiendo el response"+response)
            //Use database as cache
            if (response.count > 0 ){
                petDoa.insertPet(response.pets)

            }
            ApiResponse.Success(data = petDoa.getAllPetCards())
        } catch (e: HttpException) {
            //handles exception with the request
            ApiResponse.Error(exception = e)
        } catch (e: IOException){
            //handles no internet exception
            ApiResponse.Error(exception = e)
        }
    }

}


