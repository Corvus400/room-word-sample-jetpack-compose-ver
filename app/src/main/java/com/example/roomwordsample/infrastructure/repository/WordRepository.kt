package com.example.roomwordsample.infrastructure.repository

import androidx.annotation.WorkerThread
import com.example.roomwordsample.domain.entity.Word
import com.example.roomwordsample.infrastructure.database.dao.WordDao
import kotlinx.coroutines.flow.Flow

class WordRepository(
    private val wordDao: WordDao
) {
    val allWords: Flow<List<Word>> = wordDao.getAlphabetizedWords()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }
}