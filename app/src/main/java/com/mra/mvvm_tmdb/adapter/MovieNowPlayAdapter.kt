package com.mra.mvvm_tmdb.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.mra.mvvm_tmdb.R
import com.mra.mvvm_tmdb.model.data.NowplayItem
import com.mra.mvvm_tmdb.ui.Detail.DetailInfo
import kotlinx.android.synthetic.main.item_nowplay.view.*

class MovieNowPlayAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private val util = com.mra.mvvm_tmdb.util.util()
        var n_adult: Boolean = false
        var n_backdropPath: String = ""
        var n_oLanguage: String = ""
        var n_oTitle: String = ""
        var n_overview: String = ""
        var n_posterPath: String = ""
        var n_releaseDate: String = ""
        var n_title: String = ""
        var n_id: Int = 0
    }

    private val nowplayItemList = ArrayList<NowplayItem>()

    class nowplayHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_nowplay, parent, false)
    ) {
        fun onBind(item: NowplayItem) {
            itemView.run {
                item_nowplay_view.run {
                    val urls = util.changeNowMovieUrl(item.poster_path)
                    Log.d("IMG PATH DATA ", urls)
                    val circularProgressDrawable = CircularProgressDrawable(context)
                    circularProgressDrawable.strokeWidth = 5f
                    circularProgressDrawable.centerRadius = 30f
                    circularProgressDrawable.setColorSchemeColors(
                        R.color.white
                    )
                    circularProgressDrawable.start()
                    Glide.with(this)
                        .load(urls)
                        .centerCrop()
                        .placeholder(circularProgressDrawable)
                        .error(R.drawable.outline_error_outline_white_24dp)
                        .into(this)

                    setOnClickListener {
                        val intent = Intent(context, DetailInfo::class.java)
                        intent.putExtra("id", item.id)
                        context.startActivity(intent)
                    }
                }

                tv_title.run {
                    text = item.title
                }


            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        nowplayHolder(
            parent
        )

    override fun getItemCount(): Int = nowplayItemList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? nowplayHolder)?.onBind(nowplayItemList[position])
    }

    fun refresh() {
        nowplayItemList.clear()
    }

    fun addNowPlayItem(
        id: Int?,
        adult: Boolean?,
        backdropPath: String?,
        originalLanguage: String?,
        originalTitle: String?,
        overview: String?,
        posterPath: String?,
        releaseDate: String?,
        title: String?
    ) {
        id?.let { n_id = id }
        adult?.let { n_adult = adult }
        backdropPath?.let { n_backdropPath = backdropPath }
        originalLanguage?.let { n_oLanguage = originalLanguage }
        originalTitle?.let { n_oTitle = originalTitle }
        overview?.let { n_overview = overview }
        posterPath?.let { n_posterPath = posterPath }
        releaseDate?.let { n_releaseDate = releaseDate }
        title?.let { n_title = title }

        nowplayItemList.add(
            NowplayItem(
                n_id,
                n_adult,
                n_backdropPath,
                n_oLanguage,
                n_oTitle,
                n_overview,
                n_posterPath,
                n_releaseDate,
                n_title
            )
        )
    }

}


