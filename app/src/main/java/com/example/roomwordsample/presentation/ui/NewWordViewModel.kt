package com.example.roomwordsample.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.roomwordsample.domain.entity.Word
import com.example.roomwordsample.infrastructure.repository.WordRepository
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class NewWordViewModel(
    private val repository: WordRepository
) : ViewModel() {
    fun insert(word: Word) = viewModelScope.launch {
        repository.insert(word)
    }

    class NewWordViewModelFactory(private val repository: WordRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(NewWordViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return NewWordViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}