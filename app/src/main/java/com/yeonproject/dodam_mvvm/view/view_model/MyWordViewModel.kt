package com.yeonproject.dodam_mvvm.view.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yeonproject.dodam_mvvm.data.model.MyWordItem
import com.yeonproject.dodam_mvvm.data.repository.Callback
import com.yeonproject.dodam_mvvm.data.repository.my_word.MyWordRepository
import com.yeonproject.dodam_mvvm.data.room.entity.MyWordEntity

class MyWordViewModel(
    private val repository: MyWordRepository
) : ViewModel() {
    val myWordList = MutableLiveData<List<MyWordItem>>()
    val myWord = MutableLiveData<MyWordEntity>()
    val result = MutableLiveData<Boolean>()
    fun getMyWordList() {
        repository.getMyWord(object : Callback<List<MyWordEntity>> {
            override fun onSuccess(response: List<MyWordEntity>) {
                val items = mutableListOf<MyWordItem>()
                for (i in response.indices) {
                    items.add(response[i].toMyWordItem())
                }
                myWordList.postValue(items)
            }

            override fun onFailure(message: String) {

            }

        })
    }

    fun getMyWord(wordNumber: Int) {
        repository.getMyWordDetail(wordNumber, object : Callback<MyWordEntity> {
            override fun onSuccess(response: MyWordEntity) {
                myWord.postValue(response)
            }

            override fun onFailure(message: String) {

            }

        })
    }

    fun createMyWord(wordNumber: Int, hangul: String, english: String, image: String) {
        repository.createMyWord(wordNumber, hangul, english, image, object : Callback<Boolean> {
            override fun onSuccess(response: Boolean) {
                result.postValue(response)
            }

            override fun onFailure(message: String) {

            }

        })
    }

    fun updateMyWord(wordNumber: Int, hangul: String, english: String, image: String) {
        repository.updateMyWord(wordNumber, hangul, english, image, object : Callback<Boolean> {
            override fun onSuccess(response: Boolean) {
                result.postValue(response)
            }

            override fun onFailure(message: String) {

            }

        })
    }

    fun updateMyWord(wordNumber: Int, hangul: String, english: String) {
        repository.updateMyWord(wordNumber, hangul, english, object : Callback<Boolean> {
            override fun onSuccess(response: Boolean) {
                result.postValue(response)
            }

            override fun onFailure(message: String) {

            }

        })
    }

    fun deleteMyWord(wordNumber: Int) {
        repository.deleteMyWord(wordNumber, object : Callback<Boolean> {
            override fun onSuccess(response: Boolean) {
                result.postValue(response)
            }

            override fun onFailure(message: String) {

            }

        })
    }
}