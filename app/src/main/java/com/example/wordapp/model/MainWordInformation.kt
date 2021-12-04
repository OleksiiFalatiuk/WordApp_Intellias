package com.example.wordapp.model

data class MainWordInformation(
    val word: String,
    val phonetic: String,
    val meanings: List<Meaning>
)