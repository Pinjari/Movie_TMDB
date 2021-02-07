package com.mra.mvvm_tmdb.util

class util() {
    fun changeNowMovieUrl(url: String): String = try {
        "https://image.tmdb.org/t/p/w500$url"
    } catch (e: Exception) {
        e.printStackTrace()
        "https://image.tmdb.org/t/p/w500/isZPu087RDqQaNqJ8t0khk0nGDm.jpg"
    }

    /*https://image.tmdb.org/t/p/w500/isZPu087RDqQaNqJ8t0khk0nGDm.jpg*/


    fun cutWordLimit(txt: String): String {
        var t = ""
        val s = txt.length
        if (s >= 15) {
            val range = IntRange(0, 15)
            t = txt.slice(range)
        } else {
            t = txt
        }
        return t
    }
}