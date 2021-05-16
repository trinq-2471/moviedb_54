package com.sun.moviedb_54.screen.actordetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.sun.moviedb_54.R
import com.sun.moviedb_54.databinding.FragmentActorBinding
import com.sun.moviedb_54.extensions.addFragment
import com.sun.moviedb_54.screen.actordetail.adapter.MovieActorAdapter
import com.sun.moviedb_54.screen.detailmovie.DetailMovieFragment
import kotlinx.android.synthetic.main.fragment_actor.*
import org.koin.android.viewmodel.ext.android.viewModel

class ActorDetailFragment : Fragment() {

    private lateinit var binding: FragmentActorBinding
    private val detailActorViewModel by viewModel<ActorDetailViewModel>()
    private val movieActorAdapter by lazy {
        MovieActorAdapter {
            addFragment(DetailMovieFragment.newInstance(it), R.id.mainFrame)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_actor, container, false)
        binding.apply {
            actorViewModel = this@ActorDetailFragment.detailActorViewModel
            lifecycleOwner = this@ActorDetailFragment
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initView()
        handleEvent()
    }

    private fun initData() {
        arguments?.getInt(ARGUMENT_ACTOR_ID)?.let {
            detailActorViewModel.getActorDetail(it)
        }
    }

    private fun initView() {
        binding.movieActorAdapter = movieActorAdapter
    }

    private fun handleEvent() {
        imageBack.setOnClickListener {
            fragmentManager?.popBackStack()
        }
    }

    companion object {
        const val ARGUMENT_ACTOR_ID = "ARGUMENT_ACTOR_ID"

        fun newInstance(id: Int?) = ActorDetailFragment().apply {
            arguments = bundleOf(ARGUMENT_ACTOR_ID to id)
        }
    }
}
