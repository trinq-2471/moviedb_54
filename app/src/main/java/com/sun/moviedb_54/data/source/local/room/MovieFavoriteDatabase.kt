package com.sun.moviedb_54.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sun.moviedb_54.data.model.MovieFavorite

@Database(entities = [MovieFavorite::class], version = 1)
abstract class MovieFavoriteDatabase : RoomDatabase() {

    abstract val movieFavoriteDao : MovieFavoriteDAO
}
