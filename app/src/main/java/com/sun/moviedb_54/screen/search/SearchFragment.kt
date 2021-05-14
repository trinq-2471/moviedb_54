package com.sun.moviedb_54.screen.search

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.provider.SearchRecentSuggestions
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.cursoradapter.widget.CursorAdapter
import androidx.databinding.DataBindingUtil
import com.sun.moviedb_54.R
import com.sun.moviedb_54.databinding.FragmentSearchBinding
import com.sun.moviedb_54.extensions.addFragment
import com.sun.moviedb_54.extensions.hideKeyboard
import com.sun.moviedb_54.screen.detailmovie.DetailMovieFragment
import com.sun.moviedb_54.screen.search.adapter.SearchAdapter
import com.sun.moviedb_54.screen.search.provider.SuggestionProvider
import org.koin.android.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var searchAdapter: SearchAdapter
    private lateinit var searchManager: SearchManager
    private lateinit var suggestions: SearchRecentSuggestions
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
        registerService()
        initViews()
        handleEvents()
    }

    private fun registerService() {
        suggestions = SearchRecentSuggestions(
            requireContext(),
            SuggestionProvider.AUTHORITY,
            SuggestionProvider.MODE
        )
        searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        binding.searchView.setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))
        requireActivity().intent.apply {
            if (Intent.ACTION_SEARCH == action) {
                getStringExtra(SearchManager.QUERY)?.also { query ->
                    SearchRecentSuggestions(
                        this@SearchFragment.requireContext(),
                        SuggestionProvider.AUTHORITY,
                        SuggestionProvider.MODE
                    )
                        .saveRecentQuery(query, null)
                }
            }
        }
    }

    private fun initViews() {
        searchAdapter = SearchAdapter {
            addFragment(DetailMovieFragment.newInstance(it), R.id.mainFrame)
        }
        with(binding){
            searchAdapter = this@SearchFragment.searchAdapter
            searchView.isFocusable = true
            searchView.requestFocusFromTouch()
        }
    }

    private fun handleEvents() {
        binding.imageSearchBack.setOnClickListener {
            hideKeyboard()
            fragmentManager?.popBackStack()
        }
        handleSearchQueryText()
        handleSearchSuggestions()
    }

    private fun handleSearchQueryText() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    suggestions.saveRecentQuery(query, null)
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

    private fun handleSearchSuggestions() {
        binding.searchView.setOnSuggestionListener(object : SearchView.OnSuggestionListener {

            override fun onSuggestionSelect(position: Int): Boolean {
                return true
            }

            override fun onSuggestionClick(position: Int): Boolean {
                binding.searchView.setQuery(getQuerySearchView(position), true)
                with(viewModel) {
                    onChangeQuerySearch(getQuerySearchView(position))
                    searchMovie(getQuerySearchView(position))
                    hideKeyboard()
                }
                return true
            }
        })
    }

    private fun getQuerySearchView(position: Int): String {
        val selectedView = binding.searchView.suggestionsAdapter
        val cursor = selectedView.getItem(position) as Cursor
        val index = cursor.getColumnIndexOrThrow(SearchManager.SUGGEST_COLUMN_TEXT_1)
        return cursor.getString(index)
    }

    companion object {
        fun newInstance() = SearchFragment()
    }
}
