package com.sun.moviedb_54.screen.genres.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sun.moviedb_54.R
import com.sun.moviedb_54.data.model.GenresResult
import com.sun.moviedb_54.databinding.ItemGenresSelectedBinding
import com.sun.moviedb_54.screen.genres.adapter.itemviewholder.GenresSelectedItemViewModel
import com.sun.moviedb_54.ultis.BindingDataRecyclerView

class GenresSelectedAdapter(
    private var onClickListener: (Int?, GenresResult) -> Unit
) : ListAdapter<GenresResult, GenresSelectedAdapter.GenresSelectedViewHolder>(GenresSelectedDiffUtil()),
    BindingDataRecyclerView<MutableList<GenresResult>> {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenresSelectedViewHolder {
        val binding = DataBindingUtil.inflate<ItemGenresSelectedBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_genres_selected,
            parent,
            false
        )
        return GenresSelectedViewHolder(onClickListener, binding)
    }

    override fun onBindViewHolder(holder: GenresSelectedViewHolder, position: Int) {
        holder.bindData(getItem(position), position)
    }

    override fun setData(data: MutableList<GenresResult>?) {
        data?.let {
            submitList(it.toMutableList())
        }
    }

    class GenresSelectedViewHolder(
        private val onClickListener: (Int?, GenresResult) -> Unit,
        private val binding: ItemGenresSelectedBinding,
        private val itemViewModel: GenresSelectedItemViewModel = GenresSelectedItemViewModel(
            onClickListener
        )
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.viewModel = itemViewModel
        }

        fun bindData(genresResultSelected: GenresResult, position: Int) {
            with(itemViewModel) {
                setData(genresResultSelected)
                this.position = position
            }
            binding.executePendingBindings()
        }
    }

    class GenresSelectedDiffUtil : DiffUtil.ItemCallback<GenresResult>() {

        override fun areItemsTheSame(oldItem: GenresResult, newItem: GenresResult): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: GenresResult, newItem: GenresResult): Boolean {
            return oldItem == newItem
        }
    }
}
