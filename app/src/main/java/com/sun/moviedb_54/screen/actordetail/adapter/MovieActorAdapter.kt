package com.sun.moviedb_54.screen.actordetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sun.moviedb_54.data.model.MovieResult
import com.sun.moviedb_54.databinding.ItemRecommendationsBinding
import com.sun.moviedb_54.ultis.BindingDataRecyclerView
import com.sun.moviedb_54.ultis.NoteDiffCallBack
import kotlinx.android.synthetic.main.item_recommendations.view.*

class MovieActorAdapter(private val onClickListener: (Int) -> Unit) :
    ListAdapter<MovieResult, MovieActorViewHolder>(NoteDiffCallBack()),
    BindingDataRecyclerView<MutableList<MovieResult>> {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieActorViewHolder {
        val binding =
            ItemRecommendationsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieActorViewHolder(binding, onClickListener)
    }

    override fun onBindViewHolder(holder: MovieActorViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    override fun setData(data: MutableList<MovieResult>?) {
        data?.let { submitList(it) }
    }
}

class MovieActorViewHolder(
    private val binding: ItemRecommendationsBinding,
    private val onClickListener: (Int) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(movie: MovieResult) {
        binding.movieRecommend = movie
        binding.root.imageRecommendation.setOnClickListener {
            movie.id?.let { it1 -> onClickListener(it1) }
        }
        binding.executePendingBindings()
    }
}
