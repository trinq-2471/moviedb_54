package com.sun.moviedb_54.screen.detailmovie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sun.moviedb_54.R

class DetailMovieFragment : Fragment() {

    private var idMovie: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail_movie, container, false)
    }

    companion object {
        fun newInstance(id: Int?) = DetailMovieFragment().apply {
            idMovie = id
        }
    }
}
