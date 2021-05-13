package com.sun.moviedb_54.screen.genres.adapter.itemviewholder

import android.view.View
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.sun.moviedb_54.BR
import com.sun.moviedb_54.data.model.GenresResult
import com.sun.moviedb_54.ultis.Constant

class GenresSelectedItemViewModel(
    private var onClickListener: (Int?, GenresResult) -> Unit,
    var position: Int = Constant.POSITION_DEFAULT
) : BaseObservable() {

    @Bindable
    var genresResultSelected: GenresResult? = null

    fun setData(genresResultSelected: GenresResult?) {
        genresResultSelected?.let {
            this.genresResultSelected = it
            notifyPropertyChanged(BR.genresResultSelected)
        }
    }

    fun onItemClicked(view: View) {
        view.setOnClickListener {
            genresResultSelected?.let {
                onClickListener(it.positionSelected, it)
            }
        }
    }
}
