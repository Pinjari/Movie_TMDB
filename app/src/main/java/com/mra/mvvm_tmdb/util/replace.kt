package com.mra.mvvm_tmdb.util

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity

fun AppCompatActivity.replace(@IdRes frameId: Int, fragment: androidx.fragment.app.Fragment, tag: String? = null) {
    supportFragmentManager.beginTransaction().replace(frameId, fragment, tag).commit()

}