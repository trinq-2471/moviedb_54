package com.sun.moviedb_54.screen.favourite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sun.moviedb_54.data.model.MovieFavorite
import com.sun.moviedb_54.databinding.ItemFavoriteBinding
import com.sun.moviedb_54.ultis.BindingDataRecyclerView
import com.sun.moviedb_54.ultis.FavoriteDiffCallBack
import kotlinx.android.synthetic.main.item_favorite.view.*

class FavoriteAdapter(private val onItemClick: (Int) -> Unit) :
    ListAdapter<MovieFavorite, FavoriteAdapter.FavoriteViewHolder>(FavoriteDiffCallBack()),
    BindingDataRecyclerView<MutableList<MovieFavorite>> {

    override fun setData(data: MutableList<MovieFavorite>?) {
        data?.let {
            submitList(data.toMutableList())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding =
            ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteAdapter.FavoriteViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    class FavoriteViewHolder(
        private val binding: ItemFavoriteBinding,
        private val onItemClick: (Int) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(movie: MovieFavorite) {
            binding.movieFavorite = movie
            binding.root.imageFavoriteMovie.setOnClickListener {
                onItemClick(movie.idMovie)
            }
            binding.executePendingBindings()
        }
    }
}
