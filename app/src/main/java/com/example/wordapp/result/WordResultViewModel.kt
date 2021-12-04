package com.example.wordapp.result

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wordapp.model.MainWordInformation
import com.example.wordapp.model.Meaning
import com.example.wordapp.repository.WordRepository
import kotlinx.coroutines.launch

class WordResultViewModel(private val repository: WordRepository): ViewModel() {

    private val _wordMean = MutableLiveData<MainWordInformation>(null)
    val wordMean: LiveData<MainWordInformation> = _wordMean

    fun loadMeanings(word: String){
        viewModelScope.launch {
            _wordMean.value = repository.loadMeanings(word)
        }
    }

}