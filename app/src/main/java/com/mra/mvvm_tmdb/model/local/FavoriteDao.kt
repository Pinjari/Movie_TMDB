package com.mra.mvvm_tmdb.model.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM favorite")
    fun getAll(): LiveData<List<Favorite>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(favorite: Favorite)

    @Delete
    fun delete(favorite: Favorite)

}