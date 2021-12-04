package com.example.wordapp.repository

import com.example.wordapp.model.MainWordInformation

interface WordRepository {
    suspend fun loadMeanings(word: String): MainWordInformation
}