package com.sun.moviedb_54.module

import android.content.Context
import com.sun.moviedb_54.data.source.local.room.DatabaseBuilder
import com.sun.moviedb_54.data.source.local.room.MovieFavoriteDatabase
import org.koin.dsl.module

val storageModule = module {

    fun buildRoomDB(context: Context) = DatabaseBuilder().buildRoomDB(context)

    fun provideImageDao(movieFavoriteDatabase: MovieFavoriteDatabase) = movieFavoriteDatabase.movieFavoriteDao

    single { buildRoomDB(get()) }
    single { provideImageDao(get()) }
}
