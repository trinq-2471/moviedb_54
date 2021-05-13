package com.sun.moviedb_54.data.model

import com.google.gson.annotations.SerializedName

data class MovieResult(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("poster_path")
    val posterPath: String? = null,
    @SerializedName("vote_average")
    val voteAverage: Double? = null
)
