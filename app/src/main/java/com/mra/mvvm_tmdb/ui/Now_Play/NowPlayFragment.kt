package com.mra.mvvm_tmdb.ui.Now_Play

import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mra.mvvm_tmdb.R
import com.mra.mvvm_tmdb.adapter.EndlessRecyclerOnScrollListener
import com.mra.mvvm_tmdb.adapter.MovieNowPlayAdapter
import com.mra.mvvm_tmdb.base.BaseViewFragment
import com.mra.mvvm_tmdb.databinding.FragNowplayBinding
import com.faltenreich.skeletonlayout.applySkeleton
import com.faltenreich.skeletonlayout.createSkeleton
import kotlinx.android.synthetic.main.frag_nowplay.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class NowPlayFragment : BaseViewFragment<FragNowplayBinding, NowPlayViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.frag_nowplay

    override val viewModel: NowPlayViewModel by viewModel()

    private val movieNowPlayAdapter: MovieNowPlayAdapter by inject()
    val PAGE_START = 1
    private var CURRENT_PAGE = PAGE_START
    var isLoading = false
    private var isLastPage = false
    private var loading = true
    var pastVisiblesItems = 0
    var visibleItemCount: Int = 0
    var totalItemCount: Int = 0
    override fun initStartView() {
        var lm = GridLayoutManager(context, 2)
        movie_activity_recycler_view.run {
            adapter = movieNowPlayAdapter
            lm = GridLayoutManager(context, 2).apply {
                orientation = GridLayoutManager.VERTICAL
            }
            layoutManager = lm
            setHasFixedSize(true)


            skeletonLayout.run {
                createSkeleton()
                movie_activity_recycler_view.applySkeleton(R.layout.frag_nowplay)
                showSkeleton()
                showShimmer
            }

        }

        movie_activity_recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) { //check for scroll down
                    visibleItemCount = lm.childCount
                    totalItemCount = lm.itemCount
                    pastVisiblesItems = lm.findFirstVisibleItemPosition()
                    if (loading) {
                        if (visibleItemCount + pastVisiblesItems >= totalItemCount) {
                            loading = false
                            Log.v("...", "Last Item Wow !")
                            CURRENT_PAGE++;
                            initAfterBinding();
                            // Do pagination.. i.e. fetch new data
                        }
                    }
                }
            }
        })
        movie_activity_recycler_view.addOnScrollListener(object :
            EndlessRecyclerOnScrollListener(lm, 19) {
            override fun onLoadMore() {
                CURRENT_PAGE++;
                initAfterBinding();
            }
            /*  override fun loadMoreItems() {
                  this@NowPlayFragment.isLoading = true;
                  CURRENT_PAGE++;
                  initAfterBinding();
              }

              override fun isLastPage(): Boolean {
                  return this@NowPlayFragment.isLastPage
              }

              override fun isLoading(): Boolean {
                  return this@NowPlayFragment.isLoading
              }
  */
        })
    }


    override fun initDataBinding() {
        movieNowPlayAdapter.refresh()
        viewModel.movieResponseLiveData.observe(viewLifecycleOwner, Observer {
            it.results.forEach {
                movieNowPlayAdapter.addNowPlayItem(
                    it.id,
                    it.adult,
                    it.backdrop_path,
                    it.original_language,
                    it.original_title,
                    it.overview,
                    it.poster_path,
                    it.release_date,
                    it.title
                )
            }
            movieNowPlayAdapter.notifyDataSetChanged()
        })
    }

    override fun initAfterBinding() {
        val API_KEY = "84301bd818cef2f63643e7dffa8998ab"
        val lang: String = "In-EN"
        val region: String = "in"
        viewModel.getMovieData(API_KEY, lang, CURRENT_PAGE, region)
        loading = true
        skeletonLayout.showOriginal()

    }

    /*
    * 496243
    * */


}
