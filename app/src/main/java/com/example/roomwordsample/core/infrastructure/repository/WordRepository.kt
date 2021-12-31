package com.example.roomwordsample.core.infrastructure.repository

import androidx.annotation.WorkerThread
import com.example.roomwordsample.core.domain.entity.Word
import com.example.roomwordsample.core.infrastructure.database.dao.WordDao
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