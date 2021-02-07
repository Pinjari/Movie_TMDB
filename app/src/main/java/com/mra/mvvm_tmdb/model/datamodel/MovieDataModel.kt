package com.mra.mvvm_tmdb.model.datamodel

import com.mra.mvvm_tmdb.model.response.DetailResponse
import com.mra.mvvm_tmdb.model.response.ImageResponse
import com.mra.mvvm_tmdb.model.response.MovieResponse
import com.mra.mvvm_tmdb.model.response.SearchResponse
import io.reactivex.Single

interface MovieDataModel {
    //https://api.themoviedb.org/3/movie/now_playing?api_key=dd3529cb48a78d9d2e775be63596398a&language=ko-KR&page=1
    fun getMovieData(api_key : String, language : String, page : Int,  region:String) : Single<MovieResponse>

    fun getSearchData(api_key : String, language : String, query : String, page: Int, region : String) : Single<SearchResponse>

    fun getImageData(movie_id : Int, api_key : String) : Single<ImageResponse>

    fun getDetail(movie_id : Int, api_key : String, language : String) : Single<DetailResponse>
}