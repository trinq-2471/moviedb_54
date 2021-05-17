package com.sun.moviedb_54.screen.hot

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.sun.moviedb_54.R
import com.sun.moviedb_54.databinding.FragmentHotBinding
import com.sun.moviedb_54.extensions.addFragment
import com.sun.moviedb_54.screen.detailmovie.DetailMovieFragment
import com.sun.moviedb_54.screen.hot.adapter.HotMovieAdapter
import com.sun.moviedb_54.screen.hot.viewmodel.HotMovieViewModel
import com.sun.moviedb_54.ultis.Constant
import com.sun.moviedb_54.ultis.HotMovieType
import kotlinx.android.synthetic.main.fragment_hot.*
import org.koin.android.viewmodel.ext.android.viewModel

class HotFragment : Fragment() {

    private val hotMovieViewModel by viewModel<HotMovieViewModel>()
    private val hotMovieAdapter by lazy {
        HotMovieAdapter { addFragment(DetailMovieFragment.newInstance(it), R.id.mainFrame) }
    }
    private lateinit var binding: FragmentHotBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate<FragmentHotBinding>(
            inflater,
            R.layout.fragment_hot,
            container,
            false
        ).apply {
            viewModel = hotMovieViewModel
            lifecycleOwner = this@HotFragment
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
    }

    override fun onStart() {
        super.onStart()
        hotMovieViewModel.fetchDataHotMovie(Constant.DEFAULT_PAGE, HotMovieType.POPULAR)
    }

    private fun initView() {
        setOnClickButton()
    }

    private fun initData() {
        binding.adapter = hotMovieAdapter
    }

    private fun setOnClickButton() {
        btnPopular.setOnClickListener {
            changeDataMovie(HotMovieType.POPULAR)
            setButtonClick(btnPopular)
            setButtonNotClick(btnTopRate)
            setButtonNotClick(btnUpComing)
        }
        btnTopRate.setOnClickListener {
            changeDataMovie(HotMovieType.TOP_RATED)
            setButtonClick(btnTopRate)
            setButtonNotClick(btnPopular)
            setButtonNotClick(btnUpComing)
        }
        btnUpComing.setOnClickListener {
            changeDataMovie(HotMovieType.UP_COMING)
            setButtonClick(btnUpComing)
            setButtonNotClick(btnTopRate)
            setButtonNotClick(btnPopular)
        }
    }

    private fun changeDataMovie(typeHotMovie: HotMovieType) {
        binding.recyclerViewHotMovie.layoutManager?.scrollToPosition(0)
        with(hotMovieViewModel) {
            addHotMovieChange(typeHotMovie)
            fetchDataHotMovie(Constant.DEFAULT_PAGE, typeHotMovie)
        }
    }

    private fun setButtonClick(button: Button) {
        button.apply {
            background = ResourcesCompat.getDrawable(
                resources, R.drawable.custom_hot_screen_button, null
            )
            setTextColor(ContextCompat.getColor(context, R.color.black))
        }
    }

    private fun setButtonNotClick(button: Button) {
        button.apply {
            background = ResourcesCompat.getDrawable(
                resources, R.drawable.custom_hot_screen_button_not_click, null
            )
            setTextColor(ContextCompat.getColor(context, R.color.white))
        }
    }

    companion object {
        fun newInstance() = HotFragment()
    }
}
