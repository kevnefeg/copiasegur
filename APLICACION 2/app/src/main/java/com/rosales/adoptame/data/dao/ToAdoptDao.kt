package com.rosales.adoptame.data.dao

import androidx.room.*
import com.rosales.adoptame.data.model.ToAdopt

@Dao
interface ToAdoptDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertWord(word: ToAdopt)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertWord(words: List<ToAdopt>)

    @Update
    suspend fun updateWord(word: ToAdopt)

    @Delete
    suspend fun deleteWord(word: ToAdopt)
}