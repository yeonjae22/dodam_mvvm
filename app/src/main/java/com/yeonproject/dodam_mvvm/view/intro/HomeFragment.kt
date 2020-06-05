package com.yeonproject.dodam_mvvm.view.intro

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.yeonproject.dodam_mvvm.view.my_word.MyWordMenuActivity
import com.yeonproject.dodam_mvvm.view.song.SongListActivity
import com.yeonproject.dodam_mvvm.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {
    private lateinit var listener: OnClickListener

    interface OnClickListener {
        fun onClick(fragment: Fragment)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = (context as OnClickListener)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_home, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btn_learning.setOnClickListener {
            listener.onClick(LanguageFragment.newInstance(LEARNING))
        }

        btn_review.setOnClickListener {
            listener.onClick(LanguageFragment.newInstance(REVIEW))
        }

        btn_song.setOnClickListener {
            val intent = Intent(context, SongListActivity::class.java)
            startActivity(intent)
        }

        btn_my_workbook.setOnClickListener {
            val intent = Intent(context, MyWordMenuActivity::class.java)
            startActivity(intent)
        }
    }

    companion object {
        private const val LEARNING = 1
        private const val REVIEW = 2
    }
}