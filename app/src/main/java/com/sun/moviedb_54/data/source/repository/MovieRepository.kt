package com.sun.moviedb_54.data.source.repository

import com.sun.moviedb_54.data.model.MovieFavorite
import com.sun.moviedb_54.data.source.MovieDataSource
import com.sun.moviedb_54.ultis.HotMovieType

class MovieRepository(
    private val remote: MovieDataSource.Remote,
    private val local: MovieDataSource.Local
) {

    suspend fun getHotMovie(
        typeHotMovie: HotMovieType,
        page: Int
    ) = remote.getHotMovie(typeHotMovie, page)

    suspend fun getGenres() = remote.getGenres()

    suspend fun getGenresMovie(page: Int, genres: String) = remote.getGenresMovie(page, genres)

    suspend fun searchMovie(page: Int, query: String) = remote.searchMovie(page, query)

    suspend fun getDetailMovie(idMovie: Int) = remote.getDetailMovie(idMovie)

    suspend fun getActor(idMovie: Int) = remote.getActor(idMovie)

    suspend fun getRecommendMovie(idMovie: Int) = remote.getRecommendMovie(idMovie)

    suspend fun getTrailer(idMovie: Int) = remote.getTrailer(idMovie)

    suspend fun getMovieFavorite() = local.getMovieFavorite()

    suspend fun checkFavorite(id : Int) = local.checkFavorite(id)

    suspend fun addMovieFavorite(movieFavorite: MovieFavorite) = local.addMovieFavorite(movieFavorite)

    suspend fun deleteMovieFavorite(movieFavorite: MovieFavorite) = local.deleteMovieFavorite(movieFavorite)
}
