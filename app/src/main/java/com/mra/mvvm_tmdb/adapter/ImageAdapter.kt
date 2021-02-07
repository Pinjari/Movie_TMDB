package com.mra.mvvm_tmdb.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.mra.mvvm_tmdb.R
import com.mra.mvvm_tmdb.model.data.ImageItem
import kotlinx.android.synthetic.main.item_search_image.view.*

class ImageAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private val util = com.mra.mvvm_tmdb.util.util()
    }

    private val imageItemList = ArrayList<ImageItem>()

    class imageHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_search_image, parent, false)
    ) {
        fun onBind(item: ImageItem) {
            itemView.run {
                search_image_item.run {
                    val urls = util.changeNowMovieUrl(item.file_path)
                    val circularProgressDrawable = CircularProgressDrawable(context)
                    circularProgressDrawable.strokeWidth = 5f
                    circularProgressDrawable.centerRadius = 30f
                    circularProgressDrawable.setColorSchemeColors(
                        R.color.white
                    )
                    circularProgressDrawable.start()
                    Glide.with(this)
                        .load(urls)
                        .override(item.width, item.height)
                        .fitCenter()
                        .placeholder(circularProgressDrawable)
                        .error(R.drawable.outline_error_outline_white_24dp)
                        .into(this)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        imageHolder(
            parent
        )

    override fun getItemCount(): Int = imageItemList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? imageHolder)?.onBind(imageItemList[position])
    }

    fun refresh() {
        imageItemList.clear()
    }

    fun addImageItem(
        file_path: String,
        width: Int,
        height: Int
    ) {
        imageItemList.add(
            ImageItem(file_path, width, height)
        )
    }

}