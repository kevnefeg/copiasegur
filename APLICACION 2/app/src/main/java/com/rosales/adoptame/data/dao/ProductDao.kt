package com.rosales.adoptame.data.dao

import androidx.room.*
import com.rosales.adoptame.data.model.Product

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertWord(word: Product)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertWord(words: List<Product>)

    @Update
    suspend fun updateWord(word: Product)

    @Delete
    suspend fun deleteWord(word: Product)
}