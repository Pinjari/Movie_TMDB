package com.mra.mvvm_tmdb.ui.Search

import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.mra.mvvm_tmdb.R
import com.mra.mvvm_tmdb.adapter.MovieSearchAdapter
import com.mra.mvvm_tmdb.base.AnBaseViewFragment
import com.mra.mvvm_tmdb.databinding.FragSearchBinding
import kotlinx.android.synthetic.main.frag_search.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class SearchFragment : AnBaseViewFragment<FragSearchBinding, SearchViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.frag_search

    override val viewModel: SearchViewModel by viewModel()
    private val movieSearchAdapter: MovieSearchAdapter by inject()

    override fun initStartView() {
        val linearLayoutManager = LinearLayoutManager(this@SearchFragment.context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        search_frag_recycler_view.run {
            adapter = movieSearchAdapter
            layoutManager = linearLayoutManager
            setHasFixedSize(true)
        }
    }


    override fun initDataBinding() {
        viewModel.searchResponseLiveData.observe(viewLifecycleOwner, Observer {
            movieSearchAdapter.refresh()
            it.results.forEach { item ->
                movieSearchAdapter.addSearchItem(
                    item.id,
                    item.overview,
                    item.poster_path,
                    item.release_date,
                    item.title
                )
            }
            movieSearchAdapter.notifyDataSetChanged()
        })
    }

    override fun initAfterBinding() {
        val API_KEY = "84301bd818cef2f63643e7dffa8998ab"


        /*         dd3529cb48a78d9d2e775be63596398a*/
        val lang: String = "In-EN"
        val region: String = "in"
        btnSearch.setOnClickListener {
            viewModel.getSearchMovie(API_KEY, lang, etKeyword.text.trim().toString(), 1, region)
        }

        etKeyword.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                viewModel.getSearchMovie(API_KEY, lang, etKeyword.text.trim().toString(), 1, region)
                return@OnEditorActionListener true
            }
            false
        })
    }

}
