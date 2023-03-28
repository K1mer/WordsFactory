package com.example.wordsfactory.services

import com.example.wordsfactory.domain.WordRaw
import retrofit2.http.GET
import retrofit2.http.Path

interface DictionaryAPIScheme {
    @GET( "{word}" )
    suspend fun getWordData(
        @Path( "word" )
        param: String
    ): ArrayList<WordRaw>
}