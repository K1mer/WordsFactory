package com.example.wordsfactory.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

data class PhoneticRaw(
    val audio: String,
    val text: String
)
data class MeaningRaw(
    val partOfSpeech: String,
    val definitions: ArrayList<Definition>
)
data class WordRaw(
    val word: String,
    val phonetics: ArrayList<PhoneticRaw>,
    val meanings: ArrayList<MeaningRaw>
)
data class Definition(
    val definition: String,
    val example: String
)

@Entity
class Word(
    @PrimaryKey
    var word: String,
    var phoneticAudioSrc: String?,
    var phoneticText: String?,
    var partOfSpeech: String?,
    var definitions: List<Definition>?,
    var example: String?
) {

    constructor( data: WordRaw ) : this(
        data.word.replaceFirstChar( Char::titlecase ),
        data.phonetics?.get(0)?.audio,
        data.phonetics?.get(0)?.text,
        data.meanings?.get(0)?.partOfSpeech?.replaceFirstChar( Char::titlecase ),
        data.meanings?.get(0)?.definitions,
        data.meanings?.get(0)?.definitions?.get(0)?.example
    )
}
