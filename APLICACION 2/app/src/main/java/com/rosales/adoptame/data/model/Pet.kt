package com.rosales.adoptame.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Pet_table")
data class Pet(
    @PrimaryKey @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "age") val age: String,
    @ColumnInfo(name = "personality") val personality: String,
    @ColumnInfo(name = "vaccine") val vaccine: String,
    @ColumnInfo(name = "specie") val specie: String,
    @ColumnInfo(name = "size") val size: String,
    @ColumnInfo(name = "weight") val weight: String,
    @ColumnInfo(name = "birthdate") val birthdate: String,
    @ColumnInfo(name = "gender") val gender: String,
    @ColumnInfo(name = "history") val history: String,
    @ColumnInfo(name = "adoptionRequirement") val adoptionRequirement: String,
    @ColumnInfo(name = "photos") val photos: String,
    @ColumnInfo(name = "videos") val videos: String,
    @ColumnInfo(name = "userID") val userID: String,

)