package com.sun.moviedb_54.screen.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.sun.moviedb_54.R
import com.sun.moviedb_54.databinding.FragmentSearchBinding
import org.koin.android.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private val viewModel: SearchViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        binding.apply {
            viewModel = this@SearchFragment.viewModel
            lifecycleOwner = this@SearchFragment
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerLiveData()
    }

    private fun registerLiveData() {
        viewModel.movies.observe(viewLifecycleOwner, Observer {
        })
    }

    companion object {
        fun newInstance() = SearchFragment()
    }
}
