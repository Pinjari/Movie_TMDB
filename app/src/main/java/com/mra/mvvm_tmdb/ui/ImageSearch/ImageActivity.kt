package com.mra.mvvm_tmdb.ui.ImageSearch

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.mra.mvvm_tmdb.R
import com.mra.mvvm_tmdb.adapter.ImageAdapter
import com.mra.mvvm_tmdb.base.BaseViewActivity
import com.mra.mvvm_tmdb.databinding.ActivityImageBinding
import kotlinx.android.synthetic.main.activity_image.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ImageActivity: BaseViewActivity<ActivityImageBinding, ImageViewModel>() {
    private var m_id : Int = 0

    override val layoutResourceId: Int
        get() = R.layout.activity_image

    override val viewModel: ImageViewModel by viewModel()
    private val imageAdapter : ImageAdapter by inject()

    override fun initStartView() {
        init()
        image_activity_recycler_view.run {
            adapter = imageAdapter
            layoutManager = GridLayoutManager(context,3).apply {
                orientation = GridLayoutManager.VERTICAL
            }
            setHasFixedSize(true)
        }
    }

    private fun init(){
        intent.extras?.let {
            m_id = it.getInt("id")
        }
    }
    override fun initDataBinding() {
        viewModel.imageResponseLiveData.observe(this, Observer {
            imageAdapter.refresh()
            it.posters.forEach {
                imageAdapter.addImageItem(it.file_path,it.width,it.height)
            }
            imageAdapter.notifyDataSetChanged()
        })

    }

    override fun initAfterBinding() {
        val API_KEY = "84301bd818cef2f63643e7dffa8998ab"
        val movie_id = m_id
        viewModel.getImageData(movie_id,API_KEY)

    }


}
