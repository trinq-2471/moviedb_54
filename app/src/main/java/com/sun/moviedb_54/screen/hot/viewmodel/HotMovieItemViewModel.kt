package com.sun.moviedb_54.screen.hot.viewmodel

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.sun.moviedb_54.BR
import com.sun.moviedb_54.data.model.MovieResult

class HotMovieItemViewModel(
) : BaseObservable() {

    @Bindable
    var movieResult: MovieResult? = null

    fun setData(movieResult: MovieResult?) {
        movieResult?.let {
            this.movieResult = it
            notifyPropertyChanged(BR.movieResult)
        }
    }
}
