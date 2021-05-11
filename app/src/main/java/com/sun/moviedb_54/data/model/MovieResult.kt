package com.sun.moviedb_54.data.model

import com.google.gson.annotations.SerializedName

data class MovieResult(
    val id: Int? = null,
    val title: String? = null,
    @SerializedName("poster_path")
    val posterPath: String? = null,
    @SerializedName("vote_average")
    val voteAverage: Double? = null
)
