package com.sun.moviedb_54.screen.hot.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sun.moviedb_54.R
import com.sun.moviedb_54.data.model.MovieResult
import com.sun.moviedb_54.databinding.ItemHotMovieBinding
import com.sun.moviedb_54.databinding.ItemLoadMoreBinding
import com.sun.moviedb_54.ultis.BindingDataRecyclerView
import com.sun.moviedb_54.ultis.NoteDiffCallBack

class HotMovieAdapter(
) : ListAdapter<MovieResult, RecyclerView.ViewHolder>(NoteDiffCallBack()),
    BindingDataRecyclerView<MutableList<MovieResult>> {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_LOADING) {
            val binding = DataBindingUtil.inflate<ItemLoadMoreBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_load_more,
                parent,
                false
            )
            LoadItemViewHolder(binding)
        } else {
            val binding = DataBindingUtil.inflate<ItemHotMovieBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_hot_movie,
                parent,
                false
            )
            HotMovieViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is HotMovieViewHolder) {
            holder.bindData(getItem(position))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).id == null) VIEW_TYPE_LOADING else VIEW_TYPE_ITEM
    }

    override fun setData(data: MutableList<MovieResult>?) {
        data?.let { submitList(it.toMutableList()) }
    }

    companion object {

        const val VIEW_TYPE_LOADING = 0
        const val VIEW_TYPE_ITEM = 1
    }
}
