package com.yeonproject.dodam_mvvm.view.intro

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.yeonproject.dodam_mvvm.R
import com.yeonproject.dodam_mvvm.databinding.FragmentHomeBinding
import com.yeonproject.dodam_mvvm.view.base.BaseFragment
import com.yeonproject.dodam_mvvm.view.my_word.MyWordMenuActivity
import com.yeonproject.dodam_mvvm.view.song.SongListActivity

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private lateinit var listener: OnClickListener

    interface OnClickListener {
        fun onClick(fragment: Fragment)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = (context as OnClickListener)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.btnLearning.setOnClickListener {
            listener.onClick(LanguageFragment.newInstance(LEARNING))
        }

        binding.btnReview.setOnClickListener {
            listener.onClick(LanguageFragment.newInstance(REVIEW))
        }

        binding.btnSong.setOnClickListener {
            val intent = Intent(context, SongListActivity::class.java)
            startActivity(intent)
        }

        binding.btnMyWorkbook.setOnClickListener {
            val intent = Intent(context, MyWordMenuActivity::class.java)
            startActivity(intent)
        }
    }

    companion object {
        private const val LEARNING = 1
        private const val REVIEW = 2
    }
}