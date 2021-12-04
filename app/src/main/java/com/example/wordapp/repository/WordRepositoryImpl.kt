package com.example.wordapp.repository

import com.example.wordapp.data.RemoteDataSource
import com.example.wordapp.model.MainWordInformation

class WordRepositoryImpl(
    private val remoteDataSource: RemoteDataSource): WordRepository {

    override suspend fun loadMeanings(word: String): MainWordInformation {
        return remoteDataSource.loadMeanings(word)
    }
}