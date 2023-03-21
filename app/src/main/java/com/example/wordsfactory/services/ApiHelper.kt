package com.example.wordsfactory.services

import com.example.wordsfactory.domain.Word
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiHelper {
    companion object {
        private val retrofit = Retrofit.Builder()
                                       .baseUrl( "https://api.dictionaryapi.dev/api/v2/entries/en/" )
                                       .addConverterFactory( GsonConverterFactory.create() )
                                       .build()

        private val dictionaryService = retrofit.create( DictionaryAPIScheme::class.java )

        @JvmStatic
        suspend fun getWordInfo( searchText: String ): Word? {
            return try {
                val raw = dictionaryService.getWordData( searchText )[ 0 ]
                Word( raw )
            } catch ( _: Throwable ) {
                null
            }
        }
    }
}