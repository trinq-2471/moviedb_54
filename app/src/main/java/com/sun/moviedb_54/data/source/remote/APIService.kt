package com.sun.moviedb_54.data.source.remote

import com.sun.moviedb_54.data.model.GenresMovieResponse
import com.sun.moviedb_54.data.model.GenresResponse
import com.sun.moviedb_54.data.model.MovieResponse
import com.sun.moviedb_54.ultis.Constant
import com.sun.moviedb_54.ultis.SortType
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {

    @GET("movie/{typeHotMovie}")
    suspend fun getHotMovie(
        @Path("typeHotMovie") typeHotMovie: String,
        @Query("api_key") apiKey: String = Constant.API_KEY,
        @Query("language") language: String = Constant.BASE_LANGUAGE,
        @Query("page") page: Int
    ): Response<MovieResponse>

    @GET("genre/movie/list")
    suspend fun getGenres(
        @Query("api_key") apiKey: String = Constant.API_KEY,
        @Query("language") language: String = Constant.BASE_LANGUAGE
    ): Response<GenresResponse>

    @GET("discover/movie")
    suspend fun getMovieByGenres(
        @Query("page") page: Int,
        @Query("with_genres") genres: String,
        @Query("api_key") apiKey: String = Constant.API_KEY,
        @Query("language") language: String = Constant.BASE_LANGUAGE,
        @Query("sort_by") typeSort: String = SortType.DESC.value
    ): Response<GenresMovieResponse>
}
