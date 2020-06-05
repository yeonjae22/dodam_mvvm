package com.yeonproject.dodam_mvvm.view.my_word.presenter

import com.yeonproject.dodam_mvvm.data.room.entity.MyWordEntity

interface MyWordListContract {

    interface View {
        var presenter: Presenter
        fun showMyWordList(response: List<MyWordEntity>)
        fun showDeleteResult(response: Boolean)
    }

    interface Presenter {
        fun getMyWordList()
        fun deleteMyWord(wordNumber: Int)
    }
}