package com.yeonproject.dodam_mvvm.view.review.presenter

import com.yeonproject.dodam_mvvm.data.model.WordItem
import com.yeonproject.dodam_mvvm.data.repository.Callback
import com.yeonproject.dodam_mvvm.data.repository.word.WordRepository
import com.yeonproject.dodam_mvvm.network.model.WordResponse

class ReviewPresenter(
    private val repository: WordRepository,
    private val view: ReviewContract.View
) : ReviewContract.Presenter {
    override fun wordList(theme: String, language: String) {
        repository.getWordList(theme, object : Callback<List<WordResponse>> {
            override fun onSuccess(response: List<WordResponse>) {
                val items = mutableListOf<WordItem>()
                for (i in response.indices) {
                    items.add(response[i].toWordItem(language))
                }
                view.showWordList(items)
            }

            override fun onFailure(message: String) {

            }
        })
    }
}