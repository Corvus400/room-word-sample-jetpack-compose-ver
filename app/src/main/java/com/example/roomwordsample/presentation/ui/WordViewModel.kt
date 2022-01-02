package com.example.roomwordsample.presentation.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.roomwordsample.domain.entity.Word
import com.example.roomwordsample.infrastructure.repository.WordRepository


class WordViewModel(
    private val repository: WordRepository
) : ViewModel() {
    val allWords: LiveData<List<Word>> = repository.allWords.asLiveData()
}

class WordViewModelFactory(private val repository: WordRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WordViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WordViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}