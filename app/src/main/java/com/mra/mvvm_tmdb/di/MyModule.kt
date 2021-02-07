package com.mra.mvvm_tmdb.di

import com.mra.mvvm_tmdb.adapter.ImageAdapter
import com.mra.mvvm_tmdb.adapter.MovieNowPlayAdapter
import com.mra.mvvm_tmdb.adapter.MovieSearchAdapter
import com.mra.mvvm_tmdb.model.datamodel.MovieDataModel
import com.mra.mvvm_tmdb.model.datamodel.MovieDataModelImpl
import com.mra.mvvm_tmdb.model.service.MovieService
import com.mra.mvvm_tmdb.ui.Detail.DetailViewModel

import com.mra.mvvm_tmdb.ui.ImageSearch.ImageViewModel
import com.mra.mvvm_tmdb.ui.Now_Play.NowPlayViewModel
import com.mra.mvvm_tmdb.ui.Search.SearchViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

var retrofitMovie = module {
    val logging = HttpLoggingInterceptor()
    logging.setLevel(HttpLoggingInterceptor.Level.BODY)
    val httpClient = OkHttpClient.Builder()
    httpClient.addInterceptor(logging)

    single<MovieService> {
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
            .create(MovieService::class.java)
    }
}

var Adapter = module {
    factory { MovieNowPlayAdapter() }
    factory { MovieSearchAdapter() }
    factory { ImageAdapter() }
}

var dataModel = module {
    factory<MovieDataModel> { MovieDataModelImpl(get()) }
}

var viewModelPart = module {
    viewModel { NowPlayViewModel(get()) }
    viewModel { SearchViewModel(get()) }
    viewModel { ImageViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}

var myDiModule = listOf(retrofitMovie, Adapter, dataModel, viewModelPart)