package com.sun.moviedb_54.data.source.repository

import com.sun.moviedb_54.data.source.MovieDataSource
import com.sun.moviedb_54.ultis.HotMovieType

class MovieRepository(private val remote: MovieDataSource.Remote) {

    suspend fun getHotMovie(
        typeHotMovie: HotMovieType,
        page: Int
    ) = remote.getHotMovie(typeHotMovie, page)

    suspend fun getGenres() = remote.getGenres()

    suspend fun getGenresMovie(page: Int, genres: String) = remote.getGenresMovie(page, genres)

    suspend fun searchMovie(page: Int, query: String) = remote.searchMovie(page, query)

    suspend fun getDetailMovie(idMovie : Int) = remote.getDetailMovie(idMovie)

    suspend fun getActor(idMovie : Int) = remote.getActor(idMovie)

    suspend fun getRecommendMovie(idMovie : Int) = remote.getRecommendMovie(idMovie)
}
