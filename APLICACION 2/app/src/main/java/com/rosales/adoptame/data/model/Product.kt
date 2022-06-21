package com.rosales.adoptame.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Product_table")
data class Product(
    @PrimaryKey @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "price") val price: String,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "serviceID") val serviceID: String, //TODO: PREGUNTAR COMO PONGO QUE SEA UN OBJETO
)