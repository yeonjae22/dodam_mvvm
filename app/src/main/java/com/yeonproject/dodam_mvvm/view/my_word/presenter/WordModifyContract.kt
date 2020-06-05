package com.yeonproject.dodam_mvvm.view.my_word.presenter

import com.yeonproject.dodam_mvvm.data.room.entity.MyWordEntity

interface WordModifyContract {

    interface View {
        var presenter: Presenter
        fun showMyWord(response: MyWordEntity)
        fun showModifyResult(response: Boolean)
    }

    interface Presenter {
        fun getMyWord(wordNumber: Int)
        fun updateMyWord(wordNumber: Int, hangul: String, english: String, image: String)
        fun updateMyWord(wordNumber: Int, hangul: String, english: String)
    }
}