package com.rosales.adoptame.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Shelter_table")
data class Shelter(
    @PrimaryKey @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "address") val address: String,
    @ColumnInfo(name = "phone") val phone: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "website") val website: String,
    @ColumnInfo(name = "logo") val logo: String,
    @ColumnInfo(name = "socialmedia") val socialmedia: String, //TODO: SOCIALMEDIA COMO ES UN ARREGLO, PREGUNTAR SI AQUI LE PONEMOS STRING O ARREGLO
)