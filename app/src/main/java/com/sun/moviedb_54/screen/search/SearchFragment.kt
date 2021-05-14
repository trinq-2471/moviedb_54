package com.sun.moviedb_54.screen.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import com.sun.moviedb_54.R
import com.sun.moviedb_54.databinding.FragmentSearchBinding
import com.sun.moviedb_54.extensions.addFragment
import com.sun.moviedb_54.extensions.hideKeyboard
import com.sun.moviedb_54.screen.detailmovie.DetailMovieFragment
import com.sun.moviedb_54.screen.search.adapter.SearchAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var searchAdapter: SearchAdapter
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
        initViews()
        handleEvents()
    }

    private fun initViews() {
        searchAdapter = SearchAdapter {
            addFragment(DetailMovieFragment.newInstance(it), R.id.mainFrame)
        }
        binding.searchAdapter = searchAdapter
    }

    private fun handleEvents() {
        binding.imageSearchBack.setOnClickListener {
            hideKeyboard()
            fragmentManager?.popBackStack()
        }
        handleSearchQueryText()
    }

    private fun handleSearchQueryText() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    binding.recyclerViewSearch.layoutManager?.scrollToPosition(0)
                    viewModel.onChangeQuerySearch(it)
                    viewModel.searchMovie(it)
                }
                hideKeyboard()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }

    companion object {
        fun newInstance() = SearchFragment()
    }
}
