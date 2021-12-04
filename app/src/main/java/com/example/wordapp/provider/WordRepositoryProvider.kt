package com.example.wordapp.provider

import com.example.wordapp.repository.WordRepository

interface WordRepositoryProvider {
    fun provideWordRepository(): WordRepository
}