package com.example.roomwordsample.core.app

import android.app.Application
import com.example.roomwordsample.core.infrastructure.database.WordRoomDatabase
import com.example.roomwordsample.core.infrastructure.repository.WordRepository

class WordsApplication : Application() {
    val database by lazy { WordRoomDatabase.getDatabase(this) }
    val repository by lazy { WordRepository(database.wordDao()) }
}