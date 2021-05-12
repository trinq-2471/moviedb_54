package com.sun.moviedb_54.data.model

import com.google.gson.annotations.SerializedName

data class GenresMovieResult(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("poster_path")
    val posterPath: String?
)
