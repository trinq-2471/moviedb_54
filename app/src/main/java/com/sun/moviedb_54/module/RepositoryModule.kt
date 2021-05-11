package com.sun.moviedb_54.module

import com.sun.moviedb_54.data.source.repository.MovieRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { MovieRepository(get()) }
}
