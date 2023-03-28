package com.example.wordsfactory.services

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.wordsfactory.domain.Word

@Dao
interface DictionaryDaoScheme {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWord( word: Word )

    @Query( "SELECT * FROM word" )
    fun getAllWords(): List<Word>
}