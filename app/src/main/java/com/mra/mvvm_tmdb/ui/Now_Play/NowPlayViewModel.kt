package com.mra.mvvm_tmdb.ui.Now_Play

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mra.mvvm_tmdb.base.BaseViewModel
import com.mra.mvvm_tmdb.model.datamodel.MovieDataModel
import com.mra.mvvm_tmdb.model.response.MovieResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class NowPlayViewModel (private val model: MovieDataModel) : BaseViewModel(){

    private val TAG = "MovieViewModel"

    private val _movieResponseLiveData = MutableLiveData<MovieResponse>()
    val movieResponseLiveData: LiveData<MovieResponse>
        get() = _movieResponseLiveData

    fun getMovieData(api_key:String, language: String, page:Int, region : String) {
        addDisposable(model.getMovieData(api_key, language, page, region)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ it ->
                it.run {
                    this.results.forEach {result->
                        Log.d("movieTitle", "$result.title")
                        _movieResponseLiveData.postValue(this)
                    }
                }
            }, {
                Log.d(TAG, "response error, message : ${it.localizedMessage}")

            }))
    }
}