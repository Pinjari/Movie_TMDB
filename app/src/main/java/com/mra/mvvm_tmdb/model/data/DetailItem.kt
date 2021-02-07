package com.mra.mvvm_tmdb.model.data

data class DetailItem(
    val adult: Boolean,
    val backdrop_path: String,
    val genres: List<Genre>,
    val id: Int,
    val overview: String,
    val poster_path: String,
    val release_date: String,
    val runtime: Int,
    val title: String,
    val vote_average: Double,
    val vote_count: Int

)


data class Genre(
    val id: Int,
    val name: String
)