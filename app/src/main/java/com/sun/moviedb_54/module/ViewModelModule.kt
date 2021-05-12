package com.sun.moviedb_54.module

import com.sun.moviedb_54.screen.genres.GenresViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { GenresViewModel(get()) }
}
