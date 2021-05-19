package com.sun.moviedb_54.screen.favourite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sun.moviedb_54.data.model.MovieFavorite
import com.sun.moviedb_54.data.source.repository.MovieRepository
import com.sun.moviedb_54.extensions.plusAssign
import kotlinx.coroutines.launch

class FavoriteViewModel(private val movieRepository: MovieRepository) : ViewModel() {

//    private var _favoriteMovie = MutableLiveData<MutableList<MovieFavorite>>()
//    val favoriteMovie: LiveData<MutableList<MovieFavorite>>
//        get() = _favoriteMovie


    private var _favoriteMovie = MutableLiveData<MutableList<MovieFavorite>>()
    val favoriteMovie: LiveData<MutableList<MovieFavorite>>
        get() = _favoriteMovie

//    private lateinit var favoriteMovie : LiveData<MutableList<MovieFavorite>>



    fun getAllFavoriteMovie() {
        try {
            viewModelScope.launch {
                try {
                    launch {
//                        _favoriteMovie = movieRepository.getMovieFavorite()

                        movieRepository.getMovieFavorite()?.also {
                            _favoriteMovie.plusAssign(it)
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
