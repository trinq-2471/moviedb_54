package com.sun.moviedb_54.ultis

import androidx.recyclerview.widget.DiffUtil
import com.sun.moviedb_54.data.model.MovieResult

class NoteDiffCallBack : DiffUtil.ItemCallback<MovieResult>() {

    override fun areItemsTheSame(oldItem: MovieResult, newItem: MovieResult): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieResult, newItem: MovieResult): Boolean {
        return oldItem == newItem
    }
}
