package com.rosales.adoptame.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.rosales.adoptame.data.model.Pet

@Dao
interface PetDao {
    @Query("SELECT * FROM Pet_table")
    fun getAllPetCards(): LiveData<List<Pet>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertPet(pet: Pet)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertPet(pets: List<Pet>)

    @Update
    suspend fun updatePet(pet: Pet)

    @Delete
    suspend fun deletePet(pet: Pet)
}