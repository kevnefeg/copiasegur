package com.rosales.adoptame.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ToAdopt_table")
data class ToAdopt(
    @PrimaryKey  @ColumnInfo(name = "address") val address: String,
    @ColumnInfo(name = "message") val message: String,
    //TODO: FALTA HACERLO, AUN EN LA API NO SE COMO PONER LO DE MUCHAS IMAGENES
)