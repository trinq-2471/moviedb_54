package com.sun.moviedb_54.data.model

import com.google.gson.annotations.SerializedName

data class DetailMovie(
    val id: Int,
    val genres: List<Genre>,
    val tagLine: String,
    var isFavorite: Boolean = false,
    @SerializedName("original_title")
    val title: String,
    @SerializedName("backdrop_path")
    val photoBackdrop: String,
    @SerializedName("poster_path")
    val photoPoster: String,
    @SerializedName("vote_average")
    val rate: Double,
    @SerializedName("overview")
    val overView: String,
    @SerializedName("release_date")
    val releaseDate: String
)
