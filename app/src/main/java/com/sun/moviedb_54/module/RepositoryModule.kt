package com.sun.moviedb_54.module

import com.sun.moviedb_54.data.source.MovieDataSource
import com.sun.moviedb_54.data.source.remote.MovieRemoteDataSource
import com.sun.moviedb_54.data.source.repository.MovieRepository
import org.koin.dsl.bind
import org.koin.dsl.module

val dataSourceModule = module {
    single { MovieRemoteDataSource(get()) } bind (MovieDataSource.Remote::class)
}

val repositoryModule = module {
    single { MovieRepository(get()) }
}
