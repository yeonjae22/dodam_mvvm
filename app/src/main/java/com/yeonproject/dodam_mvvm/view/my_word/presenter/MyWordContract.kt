package com.yeonproject.dodam_mvvm.view.my_word.presenter

import com.yeonproject.dodam_mvvm.data.room.entity.MyWordEntity

interface MyWordContract {

    interface View {
        var presenter: Presenter
        fun showMyWordList(response: List<MyWordEntity>)
    }

    interface Presenter {
        fun getMyWordList()
    }
}