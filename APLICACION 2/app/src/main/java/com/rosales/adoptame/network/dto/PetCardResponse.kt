package com.rosales.adoptame.network.dto

import com.google.gson.annotations.SerializedName
import com.rosales.adoptame.data.model.Pet

data class PetCardResponse (
    @SerializedName("count")
    val count:Int,
    @SerializedName("pets")
    val pets:List<Pet>

    )
