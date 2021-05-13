package com.sun.moviedb_54.ultis

import androidx.recyclerview.widget.DiffUtil
import com.sun.moviedb_54.data.model.Actor

class ActorDiffCallBack: DiffUtil.ItemCallback<Actor>() {
    override fun areItemsTheSame(oldItem: Actor, newItem: Actor): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Actor, newItem: Actor): Boolean {
        return oldItem == newItem
    }
}
