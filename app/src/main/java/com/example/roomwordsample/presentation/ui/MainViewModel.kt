package com.example.roomwordsample.presentation.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.roomwordsample.domain.entity.Word
import com.example.roomwordsample.infrastructure.repository.WordRepository


class MainViewModel(
    private val repository: WordRepository
) : ViewModel() {
    val allWords: LiveData<List<Word>> = repository.allWords.asLiveData()
}

class MainViewModelFactory(private val repository: WordRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}