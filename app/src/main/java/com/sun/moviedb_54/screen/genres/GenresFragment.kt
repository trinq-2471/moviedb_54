package com.sun.moviedb_54.screen.genres

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.sun.moviedb_54.R
import com.sun.moviedb_54.databinding.FragmentGenresBinding
import com.sun.moviedb_54.ultis.Status
import org.koin.android.viewmodel.ext.android.viewModel

class GenresFragment : Fragment() {

    private lateinit var binding: FragmentGenresBinding
    private val viewModel: GenresViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_genres, container, false)
        binding.apply {
            viewModel = this@GenresFragment.viewModel
            lifecycleOwner = this@GenresFragment
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerLiveData()
    }

    private fun registerLiveData() {
        genresResultObserve()
        genresMovieResultObserve()
    }

    private fun genresResultObserve() {
        viewModel.genresResult.observe(viewLifecycleOwner, Observer { resource ->
            val data = resource.data
            when (resource.status) {
                Status.SUCCESS -> {
                }
                Status.ERROR -> {
                    Toast.makeText(requireContext(), resource.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun genresMovieResultObserve() {
        viewModel.genresMovieResult.observe(viewLifecycleOwner, Observer { resource ->
            val data = resource.data
            when (resource.status) {
                Status.SUCCESS -> {
                }
                Status.ERROR -> {
                    Toast.makeText(requireContext(), resource.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    companion object {
        fun newInstance() = GenresFragment()
    }
}
