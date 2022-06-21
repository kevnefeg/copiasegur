package com.rosales.adoptame.data.dao

import androidx.room.*
import com.rosales.adoptame.data.model.Service

@Dao
interface ServiceDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertWord(word: Service)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertWord(words: List<Service>)

    @Update
    suspend fun updateWord(word: Service)

    @Delete
    suspend fun deleteWord(word: Service)
}