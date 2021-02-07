package com.mra.mvvm_tmdb.model.datamodel

import com.mra.mvvm_tmdb.model.response.DetailResponse
import com.mra.mvvm_tmdb.model.response.ImageResponse
import com.mra.mvvm_tmdb.model.response.MovieResponse
import com.mra.mvvm_tmdb.model.response.SearchResponse
import com.mra.mvvm_tmdb.model.service.MovieService
import io.reactivex.Single

class MovieDataModelImpl(private val service: MovieService): MovieDataModel {

    /*val API_KEY = "dd3529cb48a78d9d2e775be63596398a"*/
    override fun getMovieData(api_key: String, language: String, page: Int, region:String): Single<MovieResponse> {
        return service.getNowMovie(api_key = api_key, page = page)
    }

    override fun getSearchData(api_key: String, language: String, query: String, page: Int, region: String): Single<SearchResponse> {
        return service.getSearchMovie(api_key = api_key, language = language,query = query, page = page, region=region)
    }

    override fun getImageData(movie_id: Int, api_key: String): Single<ImageResponse> {
        return service.getImageData(movie_id = movie_id, api_key = api_key)
    }

    override fun getDetail(movie_id: Int, api_key: String, language: String): Single<DetailResponse> {
        return service.getDetail(movie_id = movie_id, api_key = api_key)
    }


}