package com.yeonproject.dodam_mvvm.view.my_word.presenter

import com.yeonproject.dodam_mvvm.data.repository.Callback
import com.yeonproject.dodam_mvvm.data.repository.my_word.MyWordRepository
import com.yeonproject.dodam_mvvm.data.room.entity.MyWordEntity

class WordModifyPresenter(
    private val repository: MyWordRepository,
    private val view: WordModifyContract.View
) : WordModifyContract.Presenter {
    override fun getMyWord(wordNumber: Int) {
        repository.getMyWordDetail(wordNumber, object : Callback<MyWordEntity> {
            override fun onSuccess(response: MyWordEntity) {
                view.showMyWord(response)
            }

            override fun onFailure(message: String) {

            }

        })
    }


    override fun updateMyWord(wordNumber: Int, hangul: String, english: String, image: String) {
        repository.updateMyWord(wordNumber, hangul, english, image, object : Callback<Boolean> {
            override fun onSuccess(response: Boolean) {
                view.showModifyResult(response)
            }

            override fun onFailure(message: String) {

            }

        })
    }

    override fun updateMyWord(wordNumber: Int, hangul: String, english: String) {
        repository.updateMyWord(wordNumber, hangul, english, object : Callback<Boolean> {
            override fun onSuccess(response: Boolean) {
                view.showModifyResult(response)
            }

            override fun onFailure(message: String) {

            }

        })
    }
}