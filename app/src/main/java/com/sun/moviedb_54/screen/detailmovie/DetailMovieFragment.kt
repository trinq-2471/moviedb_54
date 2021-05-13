package com.sun.moviedb_54.screen.detailmovie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.sun.moviedb_54.R
import com.sun.moviedb_54.databinding.FragmentDetailMovieBinding
import com.sun.moviedb_54.extensions.addFragment
import org.koin.android.viewmodel.ext.android.viewModel

class DetailMovieFragment : Fragment() {

    private lateinit var binding: FragmentDetailMovieBinding
    private var idMovie: Int? = null
    private val detailMovieViewModel by viewModel<DetailMovieViewModel>()
    private val recommendationAdapter by lazy {
        RecommendationAdapter {
            addFragment(DetailMovieFragment.newInstance(it), R.id.mainFrame)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_detail_movie, container, false)
        binding.apply {
            viewModel = this@DetailMovieFragment.detailMovieViewModel
            lifecycleOwner = this@DetailMovieFragment
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initView()
    }

    private fun initData() {
        idMovie?.let { detailMovieViewModel.getDetailMovie(it) }
    }

    private fun initView() {
        binding.recommendAdapter = recommendationAdapter
    }

    companion object {
        fun newInstance(id: Int?) = DetailMovieFragment().apply {
            idMovie = id
        }
    }
}
