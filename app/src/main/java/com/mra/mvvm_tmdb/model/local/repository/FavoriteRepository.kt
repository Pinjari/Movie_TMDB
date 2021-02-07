package com.mra.mvvm_tmdb.model.local.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.mra.mvvm_tmdb.model.local.Favorite
import com.mra.mvvm_tmdb.model.local.FavoriteDao
import com.mra.mvvm_tmdb.model.local.FavoriteDatabase

class FavoriteRepository(application: Application) {

    private val favoriteDatabase = FavoriteDatabase.getInstance(application)!!
    private val favoriteDao: FavoriteDao = favoriteDatabase.favoriteDao()
    private val favorites: LiveData<List<Favorite>> = favoriteDao.getAll()

    fun getAll(): LiveData<List<Favorite>> {
        return favorites
    }

    fun insert(favorite: Favorite) {
        try {
            val thread = Thread(Runnable {
                favoriteDao.insert(favorite) })
            thread.start()
        } catch (e: Exception) { }
    }

    fun delete(favorite: Favorite) {
        try {
            val thread = Thread(Runnable {
                favoriteDao.delete(favorite)
            })
            thread.start()
        } catch (e: Exception) { }
    }

}