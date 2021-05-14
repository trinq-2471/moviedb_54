package com.sun.moviedb_54.screen.actordetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sun.moviedb_54.data.model.ActorDetail
import com.sun.moviedb_54.data.model.MovieResult
import com.sun.moviedb_54.data.source.repository.MovieRepository
import com.sun.moviedb_54.extensions.plusAssign
import kotlinx.coroutines.launch

class ActorDetailViewModel(private var movieRepository: MovieRepository) : ViewModel() {

    private val _isHideNameActor = MutableLiveData<Boolean>(false)
    val isHideNameActor: MutableLiveData<Boolean>
        get() = _isHideNameActor

    private val _isHideBirthday = MutableLiveData<Boolean>(false)
    val isHideBirthday: MutableLiveData<Boolean>
        get() = _isHideBirthday

    private val _isHideAddress = MutableLiveData<Boolean>(false)
    val isHideAddress: MutableLiveData<Boolean>
        get() = _isHideAddress

    private val _isHideBiography = MutableLiveData<Boolean>(false)
    val isHideBiography: MutableLiveData<Boolean>
        get() = _isHideBiography

    private val _detailActor = MutableLiveData<ActorDetail?>()
    val detailActor: MutableLiveData<ActorDetail?>
        get() = _detailActor

    private val _actorMovie = MutableLiveData<MutableList<MovieResult?>>()
    val actorMovie: MutableLiveData<MutableList<MovieResult?>>
        get() = _actorMovie

    fun getActorDetail(idActor: Int) {
        try {
            viewModelScope.launch {
                try {
                    launch {
                        movieRepository.getActorDetail(idActor).apply {
                            body()?.let {
                                _detailActor.value = it
                                if (it.name.isNullOrEmpty()) {
                                    _isHideNameActor.value = true
                                }
                                if (it.birthday.isNullOrEmpty()) {
                                    _isHideBirthday.value = true
                                }
                                if (it.address.isNullOrEmpty()) {
                                    _isHideAddress.value = true
                                }
                                if (it.biography.isNullOrEmpty()) {
                                    _isHideBiography.value = true
                                }
                            }
                        }
                    }
                    launch {
                        movieRepository.getMovieByActor(idActor = idActor).apply {
                            body()?.let {
                                _actorMovie.plusAssign(it.results)
                            }
                        }
                    }
                } catch (e: java.lang.Exception) {
                    e.printStackTrace()
                }
            }
        } catch (e: Exception) {
            e.localizedMessage
        }
    }
}
