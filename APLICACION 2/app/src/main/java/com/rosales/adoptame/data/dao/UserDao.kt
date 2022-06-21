package com.rosales.adoptame.data.dao

import androidx.room.*
import com.rosales.adoptame.data.model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertWord(word: User)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertWord(words: List<User>)

    @Update
    suspend fun updateWord(word: User)

    @Delete
    suspend fun deleteWord(word: User)
}