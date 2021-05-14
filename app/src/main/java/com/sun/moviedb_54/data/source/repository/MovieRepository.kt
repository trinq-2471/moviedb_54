package com.sun.moviedb_54.data.source.repository

import com.sun.moviedb_54.data.source.remote.APIService
import com.sun.moviedb_54.ultis.HotMovieType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepository(private val apiService: APIService) {

    suspend fun getHotMovie(typeHotMovie: HotMovieType, page: Int) = withContext(Dispatchers.IO) {
        return@withContext apiService.getHotMovie(typeHotMovie.path, page = page)
    }

    suspend fun getGenres() = withContext(Dispatchers.IO) {
        return@withContext apiService.getGenres()
    }

    suspend fun getGenresMovie(page: Int, genres: String) = withContext(Dispatchers.IO) {
        return@withContext apiService.getMovieByGenres(page, genres)
    }
}
