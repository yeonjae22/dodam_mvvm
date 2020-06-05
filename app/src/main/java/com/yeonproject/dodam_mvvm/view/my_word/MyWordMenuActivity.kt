package com.yeonproject.dodam_mvvm.view.my_word

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.yeonproject.dodam_mvvm.R

class MyWordMenuActivity : AppCompatActivity(), MyWordMenuFragment.OnClickListener,
    MyWordListFragment.OnClickListener, MyWordLanguageFragment.OnClickListener,
    MyWordFragment.OnClickListener, MyWordDetailFragment.OnClickListener {

    override fun onClick(fragment: Fragment) {
        replace(fragment, true)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_word_menu)
        replace(MyWordMenuFragment(), false)
    }

    private fun replace(fragment: Fragment, isBackStack: Boolean = true) {
        if (isBackStack) {
            supportFragmentManager.beginTransaction().replace(R.id.my_word_fragment, fragment)
                .addToBackStack(null).commit()
        } else {
            supportFragmentManager.beginTransaction().replace(R.id.my_word_fragment, fragment)
                .commit()

        }
    }
}
