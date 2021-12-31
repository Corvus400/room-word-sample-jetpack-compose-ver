package com.example.roomwordsample.core.app

import android.app.Application
import com.example.roomwordsample.core.infrastructure.database.WordRoomDatabase
import com.example.roomwordsample.core.infrastructure.repository.WordRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class WordsApplication : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { WordRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { WordRepository(database.wordDao()) }
}