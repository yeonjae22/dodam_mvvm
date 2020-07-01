package com.yeonproject.dodam_mvvm.data.model

import java.io.Serializable

data class MyWordItem(
    val wordNumber: Int = 0,
    val hangul: String = "",
    val english: String = "",
    val image: String = ""
) : Serializable