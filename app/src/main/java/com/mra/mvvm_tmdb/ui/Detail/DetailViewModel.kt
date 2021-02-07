package com.mra.mvvm_tmdb.ui.Detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mra.mvvm_tmdb.base.BaseViewModel
import com.mra.mvvm_tmdb.model.datamodel.MovieDataModel
import com.mra.mvvm_tmdb.model.response.DetailResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class DetailViewModel  (private val model: MovieDataModel) : BaseViewModel(){

    private val TAG = "Detail View Model"

    private val _detailResponseLiveData = MutableLiveData<DetailResponse>()
    val detailResponseLiveData: LiveData<DetailResponse>
        get() = _detailResponseLiveData


    fun getDetailData(movie_id:Int, api_key:String, language : String) {
        addDisposable(model.getDetail(movie_id, api_key, language)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ it ->
                it.run {
                    Log.d("Movie Title : ", this.title)
                    _detailResponseLiveData.postValue(this)
                }
            }, {
                Log.d(TAG, "response error, message : ${it.localizedMessage}")

            }))
    }

}