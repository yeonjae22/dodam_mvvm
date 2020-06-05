package com.yeonproject.dodam_mvvm.view.word.presenter

import com.yeonproject.dodam_mvvm.data.model.WordItem

interface WordContract {

    interface View {
        var presenter: Presenter
        fun showWordList(items: List<WordItem>)
    }

    interface Presenter {
        fun getWordList(theme: String, language: String)
    }
}