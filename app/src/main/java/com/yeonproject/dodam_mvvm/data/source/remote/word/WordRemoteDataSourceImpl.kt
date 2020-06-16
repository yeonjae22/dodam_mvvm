package com.yeonproject.dodam_mvvm.data.source.remote.word

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.yeonproject.dodam_mvvm.data.repository.Callback
import com.yeonproject.dodam_mvvm.network.model.WordResponse

class WordRemoteDataSourceImpl :
    WordRemoteDataSource {
    private var database = FirebaseDatabase.getInstance()

    override fun getWordList(theme: String, callback: Callback<List<WordResponse>>) {
        val myRef = database.getReference("learning").child(theme)
        val list = arrayListOf<WordResponse>()

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (snapshot in dataSnapshot.children) {
                    val word =
                        snapshot.getValue(WordResponse::class.java)
                    if (word != null) {
                        list.add(word)
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
        fun getInstance(): WordRemoteDataSource =
            WordRemoteDataSourceImpl()
    }
}