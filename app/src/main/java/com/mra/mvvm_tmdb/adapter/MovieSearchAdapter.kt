package com.mra.mvvm_tmdb.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.mra.mvvm_tmdb.R
import com.mra.mvvm_tmdb.model.data.SearchItem
import com.mra.mvvm_tmdb.ui.Detail.DetailInfo
import kotlinx.android.synthetic.main.item_search.view.*

class MovieSearchAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val searchItemList = ArrayList<SearchItem>()

    companion object {
        private val util = com.mra.mvvm_tmdb.util.util()
        var n_id: Int = 0
        var n_overview: String = ""
        var n_posterPath: String = ""
        var n_releaseDate: String = ""
        var n_title: String = ""
    }

    class searchViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_search, parent, false)
    ) {
        fun onBind(item: SearchItem) {
            itemView.run {
                tvSearchTitle.text = item.title
                tvSearchRDate.text = item.release_date
                tvSearchOverview.text = util.cutWordLimit(item.overview)

                ivSearch.run {
                    val urls = util.changeNowMovieUrl(item.poster_path)
                    val circularProgressDrawable = CircularProgressDrawable(context)
                    circularProgressDrawable.strokeWidth = 5f
                    circularProgressDrawable.centerRadius = 30f
                    circularProgressDrawable.setColorSchemeColors(
                        R.color.white
                    )
                    circularProgressDrawable.start()
                    Glide.with(this)
                        .load(urls)
                        .fitCenter()
                        .placeholder(circularProgressDrawable)
                        .error(R.drawable.outline_error_outline_white_24dp)
                        .into(this)

                    setOnClickListener {
                        val intent = Intent(context, DetailInfo::class.java)
                        intent.putExtra("id", item.id)
                        context.startActivity(intent)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        searchViewHolder(
            parent
        )


    override fun getItemCount(): Int = searchItemList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? searchViewHolder)?.onBind(searchItemList[position])
    }

    fun addSearchItem(
        id: Int?,
        overview: String?,
        posterPath: String?,
        releaseDate: String?,
        title: String?
    ) {
        id?.let { n_id = id }
        overview?.let { n_overview = overview }
        posterPath?.let { n_posterPath = posterPath }
        releaseDate?.let { n_releaseDate = releaseDate }
        title?.let { n_title = title }

        searchItemList.add(
            SearchItem(n_id, n_posterPath, n_releaseDate, n_title, n_overview)
        )
    }

    fun refresh() {
        searchItemList.clear()
    }

}