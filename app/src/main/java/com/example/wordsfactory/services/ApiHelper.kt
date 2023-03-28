package com.example.wordsfactory.services

import androidx.room.Room
import com.example.wordsfactory.WordsFactoryApplication
import com.example.wordsfactory.domain.Word
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiHelper {
    companion object {
        private val retrofit = Retrofit.Builder()
                                       .baseUrl( "https://api.dictionaryapi.dev/api/v2/entries/en/" )
                                       .addConverterFactory( GsonConverterFactory.create() )
                                       .build()

        private val database = WordsFactoryApplication.context
                               ?.let { Room.databaseBuilder( it,
                                       DictionaryDatabase::class.java,
                                       "db_wordsfactory" ).build() }

        private val retrofitService = retrofit.create( DictionaryAPIScheme::class.java )

        @JvmStatic
        suspend fun getWordInfo( searchText: String ): Word? {
            return try {
                try {
                    val raw = retrofitService.getWordData( searchText )[ 0 ]
                    Word( raw )
                } catch ( _: Throwable ) {
                    getSavedWord( searchText )
                }
            } catch ( _: Throwable ) {
                null
            }
        }

        fun saveWord( word: Word ) {
            database?.dictionaryDao()?.insertWord( word )
        }

        fun getSavedWord( text: String ): Word? {
            return database?.dictionaryDao()?.getAllWords()?.find { word -> word.word == text }
        }

        fun getAllWords(): ArrayList<Word> {
            return ArrayList( database?.dictionaryDao()?.getAllWords() )
        }
    }
}