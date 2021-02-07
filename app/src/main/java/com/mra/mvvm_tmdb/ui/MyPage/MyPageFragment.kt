package com.mra.mvvm_tmdb.ui.MyPage

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mra.mvvm_tmdb.R
import com.mra.mvvm_tmdb.ui.MyPage.Add.ListActivity
import kotlinx.android.synthetic.main.fragment_mypage.*

class MyPageFragment : Fragment() {

    private lateinit var myPageViewModel: MyPageViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_mypage, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnAdd.setOnClickListener {

            val intent = Intent(context,ListActivity::class.java)
            startActivity(intent)
        }
    }
}
