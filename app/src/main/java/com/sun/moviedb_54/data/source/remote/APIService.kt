package com.sun.moviedb_54.data.source.remote

import com.sun.moviedb_54.data.model.MovieResponse
import com.sun.moviedb_54.ultis.Constant
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET("movie/popular?")
    suspend fun getPopularMovie(
        @Query("api_key") apiKey: String = Constant.API_KEY,
        @Query("language") language: String = Constant.BASE_LANGUAGE,
        @Query("page") page: Int
    ): Response<MovieResponse>
}
