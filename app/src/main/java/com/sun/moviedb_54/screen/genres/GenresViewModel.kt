package com.sun.moviedb_54.screen.genres

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sun.moviedb_54.data.model.GenresResult
import com.sun.moviedb_54.data.model.GenresMovieResult
import com.sun.moviedb_54.data.source.repository.MovieRepository
import com.sun.moviedb_54.extensions.plusAssign
import com.sun.moviedb_54.ultis.Constant
import com.sun.moviedb_54.ultis.Constant.STRING_EMPTY
import com.sun.moviedb_54.ultis.LoadMoreRecyclerViewListener
import kotlinx.coroutines.launch

class GenresViewModel(private val movieRepository: MovieRepository) : ViewModel(),
    LoadMoreRecyclerViewListener {

    private val _genres = MutableLiveData<MutableList<GenresResult>>()
    val genresResults: MutableLiveData<MutableList<GenresResult>>
        get() = _genres
    private val _genresMovies = MutableLiveData<MutableList<GenresMovieResult>>()
    val genresMovieResults: MutableLiveData<MutableList<GenresMovieResult>>
        get() = _genresMovies
    private val _genresSelectedResults = MutableLiveData<MutableList<GenresResult>>()
    val genresSelectedResults: MutableLiveData<MutableList<GenresResult>>
        get() = _genresSelectedResults
    private val _isLoad = MutableLiveData<Boolean>(false)
    val isLoad: MutableLiveData<Boolean>
        get() = _isLoad
    private var currentPage = Constant.DEFAULT_PAGE

    init {
        getGenres()
    }

    override fun onLoadData() {
        _isLoad.value = true
        addLoadingGenresMovie()
        Handler(Looper.getMainLooper()).postDelayed({
            removeLoadingGenresMovie()
            currentPage++
            getGenresMovie(getGenresSelected(), currentPage)
            _isLoad.value = false
        }, Constant.DELAY_SECOND)
    }

    private fun getGenres() {
        viewModelScope.launch {
            try {
                movieRepository.getGenres().apply {
                    body()?.let {
                        _genres.plusAssign(it.results)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getGenresMovie(genres: String, page: Int = Constant.DEFAULT_PAGE) {
        viewModelScope.launch {
            try {
                movieRepository.getGenresMovie(page, genres).apply {
                    body()?.let {
                        _genresMovies.plusAssign(it.results)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun addLoadingGenresMovie() {
        _genresMovies.plusAssign(GenresMovieResult())
    }

    private fun removeLoadingGenresMovie() {
        if (_isLoad.value == true) {
            _genresMovies.value?.let {
                it.removeAt(it.size - 1)
            }
        }
    }

    fun addGenresSelected(genresResult: GenresResult) {
        currentPage = Constant.DEFAULT_PAGE
        _genresMovies.value?.clear()
        _genresSelectedResults.plusAssign(genresResult)
    }

    fun removeGenresSelected(genresResult: GenresResult) {
        val value = _genresSelectedResults.value ?: mutableListOf()
        value.remove(genresResult)
        _genresMovies.value?.clear()
        _genresSelectedResults.postValue(value)
    }

    fun getGenresSelected(): String {
        return _genresSelectedResults.value?.joinToString(Constant.SEPARATOR) {
            it.id.toString()
        } ?: STRING_EMPTY
    }
}
