package com.sun.moviedb_54.data.model

import com.google.gson.annotations.SerializedName

data class GenresMovieResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: MutableList<GenresMovieResult>
)
