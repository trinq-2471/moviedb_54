package com.sun.moviedb_54.data.model

import com.google.gson.annotations.SerializedName

data class GenresResponse(
    @SerializedName("genres")
    val results: MutableList<GenresResult>
)
