package com.sun.moviedb_54.data.source.repository

import com.sun.moviedb_54.data.source.remote.APIService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepository(private val apiService: APIService) {

    suspend fun getPopularMovie(page: Int) = withContext(Dispatchers.IO) {
        return@withContext apiService.getPopularMovie(page = page)
    }
}
