package com.sun.moviedb_54.screen.hot.adapter

import androidx.recyclerview.widget.RecyclerView
import com.sun.moviedb_54.data.model.MovieResult
import com.sun.moviedb_54.databinding.ItemHotMovieBinding
import com.sun.moviedb_54.databinding.ItemLoadMoreBinding
import com.sun.moviedb_54.screen.hot.viewmodel.HotMovieItemViewModel
import kotlinx.android.synthetic.main.item_hot_movie.view.*

class HotMovieViewHolder(
    private val binding: ItemHotMovieBinding,
    private val onClickListener: (Int) -> Unit,
    private val itemViewModel: HotMovieItemViewModel = HotMovieItemViewModel(onClickListener)
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.viewModel = itemViewModel
    }

    fun bindData(movieResult: MovieResult) {
        binding.root.imageHotMovie.setOnClickListener {
            movieResult.id?.let { it1 -> onClickListener(it1) }
        }
        itemViewModel.setData(movieResult)
        binding.executePendingBindings()
    }
}

class LoadItemViewHolder(binding: ItemLoadMoreBinding) :
    RecyclerView.ViewHolder(binding.root)
