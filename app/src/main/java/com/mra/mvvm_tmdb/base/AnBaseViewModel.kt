package com.mra.mvvm_tmdb.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class AnBaseViewModel : ViewModel(){


    private val compositeDisposable = CompositeDisposable()



    fun addDisposable(disposable: Disposable){
        compositeDisposable.add(disposable)
    }


}