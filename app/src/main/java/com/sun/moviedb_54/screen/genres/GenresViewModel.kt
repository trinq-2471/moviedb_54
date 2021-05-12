package com.sun.moviedb_54.screen.genres

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sun.moviedb_54.data.model.GenresResult
import com.sun.moviedb_54.data.model.GenresMovieResult
import com.sun.moviedb_54.data.source.repository.MovieRepository
import com.sun.moviedb_54.extensions.plusAssignResource
import com.sun.moviedb_54.ultis.Resource
import kotlinx.coroutines.launch

class GenresViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    private val _genres = MutableLiveData<Resource<MutableList<GenresResult>>>()
    val genresResult: MutableLiveData<Resource<MutableList<GenresResult>>>
        get() = _genres
    private val _genresMovies = MutableLiveData<Resource<MutableList<GenresMovieResult>>>()
    val genresMovieResult: MutableLiveData<Resource<MutableList<GenresMovieResult>>>
        get() = _genresMovies
    private val _genresSelected = MutableLiveData<MutableList<GenresResult>>()
    val genresResultSelected: MutableLiveData<MutableList<GenresResult>>
        get() = _genresSelected
    private var isLoading = MutableLiveData<Boolean>()

    init {
        getGenres()
    }

    fun getGenres() {
        viewModelScope.launch {
            try {
                movieRepository.getGenres().apply {
                    body()?.let {
                        _genres.postValue(Resource.success(it.results))
                    }
                }
            } catch (e: Exception) {
                _genres.postValue(Resource.error(null, e.localizedMessage))
            }
        }
    }

    fun getGenresMovie(page: Int, genres: String) {
        viewModelScope.launch {
            try {
                movieRepository.getGenresMovie(page, genres).apply {
                    body()?.let {
                        _genresMovies.plusAssignResource(it.results)
                    }
                }
            } catch (e: Exception) {
                _genresMovies.postValue(Resource.error(null, e.localizedMessage))
            }
        }
    }

    fun addGenresSelected(genresResult: GenresResult) {
        _genresSelected.value?.add(genresResult)
    }

    fun removeGenresSelected(positionRemove: Int) {
        _genresSelected.value?.removeAt(positionRemove)
    }
}
