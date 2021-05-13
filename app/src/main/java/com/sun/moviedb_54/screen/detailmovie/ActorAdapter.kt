package com.sun.moviedb_54.screen.detailmovie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sun.moviedb_54.data.model.Actor
import com.sun.moviedb_54.databinding.ItemActorBinding
import com.sun.moviedb_54.ultis.BindingDataRecyclerView
import com.sun.moviedb_54.ultis.ActorDiffCallBack

class ActorAdapter(private val onItemClick: (Int) -> Unit) :
    ListAdapter<Actor, ActorAdapter.ActorViewHolder>(ActorDiffCallBack()),
    BindingDataRecyclerView<MutableList<Actor>> {

    override fun setData(data: MutableList<Actor>?) {
        data?.let {
            submitList(data.toMutableList())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        val binding =
            ItemActorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ActorViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        getItem(position).apply {
            this.actorImage?.let {
                holder.onBind(this)
            }
        }
    }

    class ActorViewHolder(
        private val binding: ItemActorBinding,
        private val onItemClick: (Int) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(actor: Actor) {
            binding.actor = actor
            binding.executePendingBindings()
        }
    }
}
