package com.mra.mvvm_tmdb.util

import android.app.Activity
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.mra.mvvm_tmdb.R

class imageLoad() {

    fun backgroundLoad(view: ImageView, activity: Activity, path: String) {
        Glide.with(activity)
            .load(path)
            .centerCrop()
            .placeholder(getPlaceHolder(activity))
            .error(R.drawable.outline_error_outline_white_24dp)
            .into(view)
    }

    fun mainLoad(view: ImageView, activity: Activity, path: String) {
        Glide.with(activity)
            .load(path)
            .error(R.drawable.outline_error_outline_white_24dp)
            .fitCenter()
            .placeholder(getPlaceHolder(activity))
            .into(view)
    }

    private fun getPlaceHolder(activity: Activity): Drawable {
        val circularProgressDrawable = CircularProgressDrawable(activity)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.setColorSchemeColors(
            R.color.white
        )
        circularProgressDrawable.start()
        return circularProgressDrawable
    }


}