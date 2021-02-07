package com.mra.mvvm_tmdb.ui.Search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mra.mvvm_tmdb.base.AnBaseViewModel
import com.mra.mvvm_tmdb.model.datamodel.MovieDataModel
import com.mra.mvvm_tmdb.model.response.SearchResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SearchViewModel ( private val model: MovieDataModel) : AnBaseViewModel(){
    private val TAG = "SearchViewModel"

    private val _searchResponseLiveData = MutableLiveData<SearchResponse>()
    val searchResponseLiveData: LiveData<SearchResponse>
        get() = _searchResponseLiveData

    fun getSearchMovie(api_key:String, language: String, query:String, page:Int, region : String) {
        addDisposable(model.getSearchData(api_key, language,query, page, region)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ it ->
                it.run {
                    this.results.forEach {result->
                        Log.d("movieTitle", "$result.title")
                        _searchResponseLiveData.postValue(this)
                    }
                }
            }, {
                Log.d(TAG, "response error, message : ${it.localizedMessage}")

            }))
    }
}