package com.yeonproject.dodam_mvvm.data.repository

interface Callback<T> {
    fun onSuccess(response: T)
    fun onFailure(message: String)
}