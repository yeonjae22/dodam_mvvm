package com.yeonproject.dodam_mvvm.data.source.remote.song

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.yeonproject.dodam_mvvm.data.repository.Callback
import com.yeonproject.dodam_mvvm.network.model.SongResponse

class SongRemoteDataSourceImpl private constructor() :
    SongRemoteDataSource {
    private var database = FirebaseDatabase.getInstance()

    override fun getSongList(callback: Callback<List<SongResponse>>) {
        val myRef = database.getReference("video")
        val list = arrayListOf<SongResponse>()

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (snapshot in dataSnapshot.children) {
                    val song =
                        snapshot.getValue(SongResponse::class.java)
                    if (song != null) {
                        list.add(song)
                    }
                }
                callback.onSuccess(list)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("tag", "Failed to read value.", error.toException())
            }
        })
    }

    companion object {
        fun getInstance(): SongRemoteDataSource =
            SongRemoteDataSourceImpl()
    }
}