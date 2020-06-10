package com.yeonproject.dodam_mvvm.view.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yeonproject.dodam_mvvm.data.model.WordItem
import com.yeonproject.dodam_mvvm.data.repository.Callback
import com.yeonproject.dodam_mvvm.data.repository.word.WordRepository
import com.yeonproject.dodam_mvvm.network.model.WordResponse

class WordViewModel(
    private val repository: WordRepository
) : ViewModel() {
    val wordList = MutableLiveData<List<WordItem>>()
    fun getWordList(theme: String, language: String) {
        repository.getWordList(theme, object : Callback<List<WordResponse>> {
            override fun onSuccess(response: List<WordResponse>) {
                val items = mutableListOf<WordItem>()
                for (i in response.indices) {
                    items.add(response[i].toWordItem(language))
                }
                wordList.postValue(items)
            }

            override fun onFailure(message: String) {

            }
        })
    }
}