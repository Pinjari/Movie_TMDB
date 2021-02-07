package com.mra.mvvm_tmdb.model.data

data class SearchItem(
    val id : Int,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val overview: String
)