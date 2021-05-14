package com.sun.moviedb_54.screen.search.adapter.itemviewmodel

import android.view.View
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.sun.moviedb_54.BR
import com.sun.moviedb_54.data.model.MovieResult

class SearchItemViewModel(private val onClickListener: (Int) -> Unit) : BaseObservable() {

    @Bindable
    var movieResult: MovieResult? = null

    fun setData(movieResult: MovieResult?) {
        movieResult?.let {
            this.movieResult = it
            notifyPropertyChanged(BR.movieResult)
        }
    }

    fun onItemClicked(view: View) {
        view.setOnClickListener {
            movieResult?.let { item ->
                item.id?.let { id -> onClickListener(id) }
            }
        }
    }
}
