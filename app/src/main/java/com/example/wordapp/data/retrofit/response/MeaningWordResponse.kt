package com.example.wordapp.data.retrofit.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class MeaningWordResponse (
    @SerialName("partOfSpeech") val partOfSpeech: String,
    @SerialName("definitions") val definitions: List<DefinitionResponse>
)