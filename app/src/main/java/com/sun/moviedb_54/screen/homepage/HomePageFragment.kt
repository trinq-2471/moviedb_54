package com.sun.moviedb_54.screen.homepage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sun.moviedb_54.screen.favourite.FavouriteFragment
import com.sun.moviedb_54.screen.genres.GenresFragment
import com.sun.moviedb_54.screen.hot.HotFragment
import com.sun.moviedb_54.screen.search.SearchFragment
import com.sun.moviedb_54.R
import com.sun.moviedb_54.extensions.addFragment
import com.sun.moviedb_54.screen.detailmovie.DetailMovieFragment
import com.sun.moviedb_54.ultis.ItemBottomNav
import kotlinx.android.synthetic.main.fragment_home_page.*

class HomePageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()
        initNavigation()
    }

    private fun initListener() {
        edtSearch.setOnClickListener {
//            addFragment(SearchFragment.newInstance(), R.id.mainFrame)
            addFragment(DetailMovieFragment.newInstance(460465), R.id.mainFrame)
        }
    }

    private fun initView() {
        val listFragment = listOf(
            HotFragment.newInstance(),
            GenresFragment.newInstance(),
            FavouriteFragment.newInstance()
        )
        fragmentManager?.let {
            viewPagerHomePage.adapter = HomePageAdapter(it, listFragment)
        }
    }

    private fun initNavigation() {
        bottomNavHomePage.setOnNavigationItemSelectedListener { it ->
            when (it.itemId) {
                R.id.itemHotPage -> {
                    viewPagerHomePage.currentItem = ItemBottomNav.HOT_ITEM.position
                    true
                }
                R.id.itemGenresPage -> {
                    viewPagerHomePage.currentItem = ItemBottomNav.GENRES_ITEM.position
                    true
                }
                R.id.itemFavoritePage -> {
                    viewPagerHomePage.currentItem = ItemBottomNav.FAVORITE_ITEM.position
                    true
                }
                else -> false
            }
        }
    }

    companion object {
        fun newInstance() = HomePageFragment()
    }
}
