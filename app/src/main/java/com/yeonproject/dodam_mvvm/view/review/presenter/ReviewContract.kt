package com.yeonproject.dodam_mvvm.view.review.presenter

import com.yeonproject.dodam_mvvm.data.model.WordItem

interface ReviewContract {

    interface View {
        var presenter: Presenter
        fun showWordList(items: List<WordItem>)
    }

    interface Presenter {
        fun wordList(theme: String, language: String)
    }
}