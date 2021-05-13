package com.sun.moviedb_54.screen.genres.adapter.itemviewholder

import android.view.View
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.sun.moviedb_54.data.model.GenresMovieResult

class GenresMovieItemViewModel(
    private val onClickListener: (Int) -> Unit
) : BaseObservable() {

    @Bindable
    var genresMovieResult: GenresMovieResult? = null

    fun setData(genresMovieResult: GenresMovieResult?) {
        genresMovieResult?.let {
            this.genresMovieResult = it
            notifyPropertyChanged(BR.genresMovieResult)
        }
    }

    fun onItemClicked(view: View) {
        view.setOnClickListener {
            genresMovieResult?.let { item ->
                item.id?.let { id -> onClickListener(id) }
            }
        }
    }
}
