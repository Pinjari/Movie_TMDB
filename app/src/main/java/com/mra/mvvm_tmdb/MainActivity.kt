package com.mra.mvvm_tmdb

import android.os.Bundle

import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import com.mra.mvvm_tmdb.ui.Now_Play.NowPlayFragment
import com.mra.mvvm_tmdb.ui.Search.SearchFragment
import com.mra.mvvm_tmdb.ui.MyPage.MyPageFragment
import com.mra.mvvm_tmdb.util.replace


class MainActivity : AppCompatActivity() {

    private val nowplayFragment : NowPlayFragment by lazy {
        NowPlayFragment()
    }

    private val searchFragment: SearchFragment by lazy{
        SearchFragment()
    }

    private val mypageFragment : MyPageFragment by lazy{
        MyPageFragment()
    }

    companion object {
        private val util = com.mra.mvvm_tmdb.util.util()
    }


    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item->
        when(item.itemId){
            R.id.navigation_nowplay->{
                replace(R.id.container, nowplayFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_search->{
                replace(R.id.container, searchFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_mypage->{
                replace(R.id.container, mypageFragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        replace(R.id.container, nowplayFragment)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

    }
}
