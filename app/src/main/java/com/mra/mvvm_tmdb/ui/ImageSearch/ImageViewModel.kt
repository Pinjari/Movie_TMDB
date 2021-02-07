package com.mra.mvvm_tmdb.ui.ImageSearch

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mra.mvvm_tmdb.base.BaseViewModel
import com.mra.mvvm_tmdb.model.datamodel.MovieDataModel
import com.mra.mvvm_tmdb.model.response.ImageResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ImageViewModel ( private val model: MovieDataModel) : BaseViewModel(){

    private val TAG = "ImageViewModel"

    private val _imageResponseLiveData = MutableLiveData<ImageResponse>()
    val imageResponseLiveData: LiveData<ImageResponse>
        get() = _imageResponseLiveData


    fun getImageData(movie_id:Int, api_key:String) {
        addDisposable(model.getImageData(movie_id, api_key)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ it ->
                it.run {
                    this.posters.forEach {
                        Log.d("movieTitle", "${it.aspect_ratio}")
                        _imageResponseLiveData.postValue(this)
                    }
                }
            }, {
                Log.d(TAG, "response error, message : ${it.localizedMessage}")

            }))
    }

}