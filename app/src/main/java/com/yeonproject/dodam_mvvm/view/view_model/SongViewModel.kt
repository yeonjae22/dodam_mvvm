package com.yeonproject.dodam_mvvm.view.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yeonproject.dodam_mvvm.data.repository.Callback
import com.yeonproject.dodam_mvvm.data.repository.song.SongRepository
import com.yeonproject.dodam_mvvm.network.model.SongResponse

class SongViewModel(
    private val repository: SongRepository
) : ViewModel() {
    val songList = MutableLiveData<List<SongResponse>>()
    fun getSongList() {
        repository.getSongList(object : Callback<List<SongResponse>> {
            override fun onSuccess(response: List<SongResponse>) {
                songList.postValue(response)
            }

            override fun onFailure(message: String) {

            }

        })
    }
}