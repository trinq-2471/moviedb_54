package com.sun.moviedb_54.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.sun.moviedb_54.data.model.MovieFavorite

@Dao
interface MovieFavoriteDAO {

//    @Query("SELECT * FROM movieFavorite")
//    suspend fun getAllMovie(): MutableLiveData<MutableList<MovieFavorite>>

    @Query("SELECT * FROM movieFavorite")
    suspend fun getAllMovie(): MutableList<MovieFavorite>

    @Query("SELECT * FROM movieFavorite WHERE idMovie = :id")
    suspend fun checkFavorite(id : Int): MovieFavorite?

    @Insert
    suspend fun addMovie(movie: MovieFavorite)

    @Delete
    suspend fun deleteMovie(movie: MovieFavorite)
}
