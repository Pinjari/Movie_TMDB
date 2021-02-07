package com.mra.mvvm_tmdb.model.service

import com.mra.mvvm_tmdb.model.response.DetailResponse
import com.mra.mvvm_tmdb.model.response.ImageResponse
import com.mra.mvvm_tmdb.model.response.MovieResponse
import com.mra.mvvm_tmdb.model.response.SearchResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MovieService {

    //https://api.themoviedb.org/3/movie/now_playing?api_key=84301bd818cef2f63643e7dffa8998ab&language=ko-KR&page=1&page=2

    @GET("movie/now_playing")
    fun getNowMovie(
        @Query("api_key") api_key:String,
//        @Query("language") language:String,
        @Query("page") page:Int,
//        @Query("region") region : String
    ): Single<MovieResponse>

    @GET("search/movie")
    fun getSearchMovie(
        @Query("api_key") api_key:String,
        @Query("language") language:String,
        @Query("query") query:String,
        @Query("page") page:Int,
        @Query("region") region : String
    ): Single<SearchResponse>

    @GET("movie/{movie_id}/images")
    fun getImageData(
        @Path("movie_id") movie_id : Int,
        @Query("api_key") api_key: String
    ): Single<ImageResponse>

    @GET("movie/{movie_id}")
    fun getDetail(
        @Path("movie_id") movie_id : Int,
        @Query("api_key") api_key:String,

    ) : Single<DetailResponse>
}