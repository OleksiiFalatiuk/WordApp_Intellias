package com.example.wordapp.data.retrofit.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class MeaningsResponse(
    @SerialName("word") val word: String,
    @SerialName("phonetic") val phonetic: String,
    @SerialName("meanings") val meanings: List<MeaningWordResponse>
)