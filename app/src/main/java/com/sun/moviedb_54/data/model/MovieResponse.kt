package com.sun.moviedb_54.data.model

data class MovieResponse(
    val page: Int,
    val results: MutableList<MovieResult?>
)
