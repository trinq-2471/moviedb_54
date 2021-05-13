package com.sun.moviedb_54.screen.genres.adapter.itemviewholder

import android.view.View
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.sun.moviedb_54.data.model.GenresResult
import com.sun.moviedb_54.ultis.Constant.POSITION_DEFAULT

class GenresItemViewModel(
    private var onClickListener: (GenresResult, Int) -> Unit,
    var position: Int = POSITION_DEFAULT
) : BaseObservable() {

    @Bindable
    var genresResult: GenresResult? = null

    fun setData(genresResult: GenresResult?) {
        genresResult?.let {
            this.genresResult = it
            notifyPropertyChanged(BR.genresResult)
        }
    }

    fun onItemClicked(view: View) {
        view.setOnClickListener {
            genresResult?.let { item ->
                if (!item.isSelected) {
                    onClickListener(item, position)
                }
            }
        }
    }
}
