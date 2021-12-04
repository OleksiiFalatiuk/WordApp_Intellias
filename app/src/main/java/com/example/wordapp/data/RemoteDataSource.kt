package com.example.wordapp.data

import com.example.wordapp.model.MainWordInformation

interface RemoteDataSource {
    suspend fun loadMeanings(word: String): MainWordInformation
}