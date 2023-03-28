package com.example.wordsfactory.services

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.wordsfactory.domain.Word

@Database( entities = [ Word::class ], version = 1 )
@TypeConverters( Converters::class )
abstract class DictionaryDatabase: RoomDatabase() {
    abstract fun dictionaryDao(): DictionaryDaoScheme
}