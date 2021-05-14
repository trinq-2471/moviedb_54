package com.sun.moviedb_54.screen.search

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sun.moviedb_54.data.model.MovieResult
import com.sun.moviedb_54.data.source.repository.MovieRepository
import com.sun.moviedb_54.extensions.plusAssign
import com.sun.moviedb_54.ultis.Constant
import com.sun.moviedb_54.ultis.LoadMoreRecyclerViewListener
import kotlinx.coroutines.launch
import java.lang.Exception

class SearchViewModel(private val movieRepository: MovieRepository) : ViewModel(),
    LoadMoreRecyclerViewListener {

    private val _movies = MutableLiveData<MutableList<MovieResult?>>()
    val movies: MutableLiveData<MutableList<MovieResult?>>
        get() = _movies

    private val _isLoad = MutableLiveData<Boolean>(false)
    val isLoad: MutableLiveData<Boolean>
        get() = _isLoad

    private val _querySearch = MutableLiveData<String>()
    val querySearch: MutableLiveData<String>
        get() = _querySearch

    private var currentPage = Constant.DEFAULT_PAGE

    override fun onLoadData() {
        _isLoad.value = true
        addLoadingMovie()
        Handler(Looper.getMainLooper()).postDelayed({
            removeLoadingMovie()
            currentPage++
            _querySearch.value?.let {
                searchMovie(it, currentPage)
            }
            _isLoad.value = false
        }, Constant.DELAY_SECOND)
    }

    fun searchMovie(query: String, page: Int = Constant.DEFAULT_PAGE) {
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

    fun onChangeQuerySearch(query: String) {
        _querySearch.postValue(query)
        _movies.value?.clear()
    }

    private fun addLoadingMovie() {
        _movies.plusAssign(MovieResult())
    }

    private fun removeLoadingMovie() {
        if (_isLoad.value == true) {
            _movies.value?.let {
                it.removeAt(it.size - 1)
            }
        }
    }
}
