package com.example.wordapp.data.retrofit.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class DefinitionResponse(
    @SerialName("definition") val definition: String
)