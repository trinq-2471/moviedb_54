package com.sun.moviedb_54.data.source.remote

import com.sun.moviedb_54.data.source.MovieDataSource
import com.sun.moviedb_54.data.source.remote.api.APIService
import com.sun.moviedb_54.ultis.HotMovieType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRemoteDataSource(private val apiService: APIService) : MovieDataSource.Remote {

    override suspend fun getHotMovie(
        typeHotMovie: HotMovieType,
        page: Int
    ) = withContext(Dispatchers.IO) {
        return@withContext apiService.getHotMovie(typeHotMovie.path, page = page)
    }

    override suspend fun getGenres() = withContext(Dispatchers.IO) {
        return@withContext apiService.getGenres()
    }

    override suspend fun getGenresMovie(page: Int, genres: String) = withContext(Dispatchers.IO) {
        return@withContext apiService.getMovieByGenres(page, genres)
    }

    override suspend fun searchMovie(page: Int, query: String) = withContext(Dispatchers.IO) {
        return@withContext apiService.searchMovie(page, query)
    }

    override suspend fun getDetailMovie(idMovie: Int) = withContext(Dispatchers.IO) {
        return@withContext apiService.getDetailMovie(idMovie)
    }

    override suspend fun getActor(idMovie: Int) = withContext(Dispatchers.IO) {
        return@withContext apiService.getActor(idMovie)
    }

    override suspend fun getRecommendMovie(idMovie: Int) = withContext(Dispatchers.IO) {
        return@withContext apiService.getRecommendMovie(idMovie)
    }

    override suspend fun getTrailer(idMovie: Int) = withContext(Dispatchers.IO) {
        return@withContext apiService.getTrailer(idMovie)
    }

    override suspend fun getActorDetail(idActor: Int) = withContext(Dispatchers.IO) {
        return@withContext apiService.getActorDetail(idActor)
    }

    override suspend fun getMovieByActor(idActor: Int) = withContext(Dispatchers.IO) {
        return@withContext apiService.getMovieByActor(idActor = idActor)
    }
}
