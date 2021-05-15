package com.sun.moviedb_54.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "movieFavorite")
@Parcelize
data class MovieFavorite(
    @PrimaryKey
    var idMovie : Int,
    @ColumnInfo(name = "title")
    var title : String? = null ,
    @ColumnInfo(name = "urlImg")
    var urlImg : String? = null
) : Parcelable
