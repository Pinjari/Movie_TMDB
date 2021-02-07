package com.mra.mvvm_tmdb.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite")
data class Favorite(

    @PrimaryKey(autoGenerate = true)
    var id: Long?,

    @ColumnInfo(name = "title")
    var movie_title: String,

    @ColumnInfo(name = "date")
    var date: String,

    @ColumnInfo(name = "memo")
    var memo: String,

    @ColumnInfo(name = "photoPath")
    var photoPath: String
) {
    constructor() : this(null, "", "", "", "")
 }