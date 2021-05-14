package com.sun.moviedb_54.screen.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sun.moviedb_54.data.model.MovieResult
import com.sun.moviedb_54.data.source.repository.MovieRepository
import com.sun.moviedb_54.extensions.plusAssign
import com.sun.moviedb_54.ultis.Constant
import kotlinx.coroutines.launch
import java.lang.Exception

class SearchViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    private val _movies = MutableLiveData<MutableList<MovieResult?>>()
    val movies: MutableLiveData<MutableList<MovieResult?>>
        get() = _movies

    fun searchMovie(query: String, page: Int = Constant.POSITION_DEFAULT) {
        viewModelScope.launch {
            try {
                movieRepository.searchMovie(page, query).apply {
                    body()?.let {
                        _movies.plusAssign(it.results)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
