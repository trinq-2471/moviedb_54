package com.sun.moviedb_54.screen.genres.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sun.moviedb_54.R
import com.sun.moviedb_54.data.model.GenresResult
import com.sun.moviedb_54.databinding.ItemGenresBinding
import com.sun.moviedb_54.screen.genres.adapter.itemviewholder.GenresItemViewModel
import com.sun.moviedb_54.ultis.BindingDataRecyclerView

class GenresAdapter(
    private val onClickListener: (GenresResult, Int) -> Unit
) : ListAdapter<GenresResult, GenresAdapter.GenresViewHolder>(GenresDiffUtil()),
    BindingDataRecyclerView<MutableList<GenresResult>> {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenresViewHolder {
        val binding = DataBindingUtil.inflate<ItemGenresBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_genres,
            parent,
            false
        )
        return GenresViewHolder(binding, onClickListener)
    }

    override fun onBindViewHolder(holder: GenresViewHolder, position: Int) {
        holder.bindData(getItem(position), position)
    }

    override fun setData(data: MutableList<GenresResult>?) {
        data?.let {
            submitList(it)
        }
    }

    fun selectedGenres(position: Int) {
        getItem(position).isSelected = true
        notifyItemChanged(position)
    }

    fun unselectedGenres(position: Int?) {
        position?.let {
            getItem(position)?.isSelected = false
            notifyItemChanged(it)
        }
    }

    class GenresViewHolder(
        private val binding: ItemGenresBinding,
        private val onClickListener: (GenresResult, Int) -> Unit,
        private val itemViewModel: GenresItemViewModel = GenresItemViewModel(onClickListener)
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.viewModel = itemViewModel
        }

        fun bindData(genresResult: GenresResult, position: Int) {
            with(itemViewModel) {
                this.position = position
                setData(genresResult)
            }
            binding.executePendingBindings()
        }
    }

    class GenresDiffUtil : DiffUtil.ItemCallback<GenresResult>() {

        override fun areItemsTheSame(oldItem: GenresResult, newItem: GenresResult): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: GenresResult, newItem: GenresResult): Boolean {
            return oldItem == newItem
        }
    }
}
