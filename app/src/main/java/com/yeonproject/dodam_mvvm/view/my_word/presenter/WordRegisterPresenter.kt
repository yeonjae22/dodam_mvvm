package com.yeonproject.dodam_mvvm.view.my_word.presenter

import com.yeonproject.dodam_mvvm.data.repository.Callback
import com.yeonproject.dodam_mvvm.data.repository.my_word.MyWordRepository

class WordRegisterPresenter(
    private val repository: MyWordRepository,
    private val view: WordRegisterContract.View
) : WordRegisterContract.Presenter {
    override fun createMyWord(wordNumber: Int, hangul: String, english: String, image: String) {
        repository.createMyWord(wordNumber, hangul, english, image, object : Callback<Boolean> {
            override fun onSuccess(response: Boolean) {
                view.showMessage(response)
            }

            override fun onFailure(message: String) {

            }

        })
    }
}