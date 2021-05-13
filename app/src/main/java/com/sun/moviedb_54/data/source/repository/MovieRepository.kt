package com.sun.moviedb_54.data.source.repository

import com.sun.moviedb_54.data.source.remote.APIService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepository(private val apiService: APIService) {

    suspend fun getPopularMovie(page: Int) = withContext(Dispatchers.IO) {
        return@withContext apiService.getPopularMovie(page = page)
    }

    suspend fun getGenres() = withContext(Dispatchers.IO) {
        return@withContext apiService.getGenres()
    }

    suspend fun getGenresMovie(page: Int, genres: String) = withContext(Dispatchers.IO) {
        return@withContext apiService.getMovieByGenres(page, genres)
    }

    suspend fun getDetailMovie(idMovie : Int) = withContext(Dispatchers.IO) {
        return@withContext apiService.getDetailMovie(id = idMovie )
    }

    suspend fun getActor(idMovie : Int) = withContext(Dispatchers.IO) {
        return@withContext apiService.getActor(id = idMovie)
    }

    suspend fun getRecommendMovie(idMovie : Int) = withContext(Dispatchers.IO) {
        return@withContext apiService.getRecommendMovie(id = idMovie)
    }
}
