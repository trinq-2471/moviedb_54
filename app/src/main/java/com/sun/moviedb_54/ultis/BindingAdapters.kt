package com.sun.moviedb_54.ultis

import android.widget.ImageView
import android.widget.RatingBar
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sun.moviedb_54.extensions.loadFromUrl
import com.sun.moviedb_54.extensions.setVoteRating

@BindingAdapter("bind:onLoadImg")
fun ImageView.loadImage(url: String?) {
    loadFromUrl(Constant.BASE_URL_IMAGE + url)
}

@BindingAdapter("bind:setVoteRating")
fun RatingBar.setVoteRating(voteAverage: Double?) {
    setVoteRating(voteAverage)
}

@BindingAdapter("bind:data")
fun <T> setData(recyclerView: RecyclerView, data: T) {
    if (recyclerView.adapter is BindingDataRecyclerView<*>) {
        (recyclerView.adapter as BindingDataRecyclerView<T>).setData(data)
    }
}

@BindingAdapter(value = ["bind:adapter"])
fun RecyclerView.setAdapter(adapter: RecyclerView.Adapter<*>) {
    this.adapter = adapter
}

@BindingAdapter(value = ["bind:isLoad", "bind:onLoadMore"])
fun RecyclerView.onScrollListener(
    isLoad: Boolean,
    loadMoreListener: LoadMoreRecyclerViewListener
) {
    clearOnScrollListeners()
    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            (recyclerView.layoutManager as LinearLayoutManager).run {
                if (dy > 0) {
                    if (!isLoad && findLastCompletelyVisibleItemPosition() == itemCount - 1) {
                        loadMoreListener.onLoadData()
                    }
                }
            }
        }
    })
}
