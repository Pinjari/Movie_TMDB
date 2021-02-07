package com.mra.mvvm_tmdb.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseViewActivity<T : ViewDataBinding, R : BaseViewModel> : AppCompatActivity(){
    lateinit var viewDataBinding: T

    abstract val layoutResourceId: Int

    abstract val viewModel: R

    abstract fun initStartView()

    abstract fun initDataBinding()

    abstract fun initAfterBinding()

    private var isSetBackButtonValid = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewDataBinding = DataBindingUtil.setContentView(this, layoutResourceId)

        initStartView()
        initDataBinding()
        initAfterBinding()
    }


}