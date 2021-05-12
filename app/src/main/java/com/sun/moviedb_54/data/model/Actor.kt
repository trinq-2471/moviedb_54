package com.sun.moviedb_54.data.model

import com.google.gson.annotations.SerializedName

data class Actor(
    val id: Int,
    val name: String,
    @SerializedName("profile_path")
    val actorImage: String
)
