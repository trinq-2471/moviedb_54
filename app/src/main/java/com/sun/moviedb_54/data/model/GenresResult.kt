package com.sun.moviedb_54.data.model

import com.google.gson.annotations.SerializedName

data class GenresResult(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    var isSelected: Boolean = false,
    var positionSelected: Int? = null
)
