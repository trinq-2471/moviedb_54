package com.sun.moviedb_54.data.source.local.room

import android.content.Context
import androidx.room.Room
import com.sun.moviedb_54.ultis.Constant

class DatabaseBuilder {

    fun buildRoomDB(context: Context) =
        Room.databaseBuilder(
            context.applicationContext,
            MovieFavoriteDatabase::class.java,
            Constant.DATABASE_NAME
        ).build()
}
