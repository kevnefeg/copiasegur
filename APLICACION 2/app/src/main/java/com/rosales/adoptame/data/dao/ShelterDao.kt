package com.rosales.adoptame.data.dao

import androidx.room.*
import com.rosales.adoptame.data.model.Shelter

@Dao
interface ShelterDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertWord(word: Shelter)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertWord(words: List<Shelter>)

    @Update
    suspend fun updateWord(word: Shelter)

    @Delete
    suspend fun deleteWord(word: Shelter)
}