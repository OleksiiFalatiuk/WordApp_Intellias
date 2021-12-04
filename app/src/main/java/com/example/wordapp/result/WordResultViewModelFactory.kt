package com.example.wordapp.result

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wordapp.repository.WordRepository

class WordResultViewModelFactory(private val repository: WordRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T = WordResultViewModel(repository) as T
}