package com.yeonproject.dodam_mvvm.network.model

import com.yeonproject.dodam_mvvm.data.model.WordItem
import java.io.Serializable

data class WordResponse @JvmOverloads constructor(
    val english: String = "",
    val hangul: String = "",
    val image: String = ""
) : Serializable {
    fun toWordItem(language: String): WordItem {
        var name = ""
        if (language == "english") {
            name = english
        } else if (language == "hangul") {
            name = hangul
        }
        return WordItem(
            name,
            image
        )
    }
}