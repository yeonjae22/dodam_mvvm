package com.yeonproject.dodam_mvvm.view.word.presenter

import com.yeonproject.dodam_mvvm.data.model.WordItem
import com.yeonproject.dodam_mvvm.data.repository.Callback
import com.yeonproject.dodam_mvvm.data.repository.word.WordRepository
import com.yeonproject.dodam_mvvm.network.model.WordResponse

class WordPresenter(
    private val repository: WordRepository,
    private val view: WordContract.View
) : WordContract.Presenter {
    override fun getWordList(theme: String, language: String) {
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