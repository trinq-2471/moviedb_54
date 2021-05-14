package com.sun.moviedb_54.data.source

import com.sun.moviedb_54.data.model.GenresMovieResponse
import com.sun.moviedb_54.data.model.GenresResponse
import com.sun.moviedb_54.data.model.MovieResponse
import com.sun.moviedb_54.ultis.HotMovieType
import retrofit2.Response

interface MovieDataSource {

    interface Remote {

        suspend fun getHotMovie(typeHotMovie: HotMovieType, page: Int): Response<MovieResponse>

        suspend fun getGenres(): Response<GenresResponse>

        suspend fun getGenresMovie(page: Int, genres: String): Response<GenresMovieResponse>

        suspend fun searchMovie(page: Int, query: String): Response<MovieResponse>
    }

    interface Local {
    }
}
