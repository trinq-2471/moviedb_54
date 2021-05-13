package com.sun.moviedb_54.screen.hot.adapter

import androidx.recyclerview.widget.RecyclerView
import com.sun.moviedb_54.data.model.MovieResult
import com.sun.moviedb_54.databinding.ItemHotMovieBinding
import com.sun.moviedb_54.databinding.ItemLoadMoreBinding
import com.sun.moviedb_54.screen.hot.viewmodel.HotMovieItemViewModel

class HotMovieViewHolder(
    private val binding: ItemHotMovieBinding,
    private val itemViewModel: HotMovieItemViewModel = HotMovieItemViewModel()
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.viewModel = itemViewModel
    }

    fun bindData(movieResult: MovieResult) {
        itemViewModel.setData(movieResult)
        binding.executePendingBindings()
    }
}

class LoadItemViewHolder(binding: ItemLoadMoreBinding) :
    RecyclerView.ViewHolder(binding.root)
