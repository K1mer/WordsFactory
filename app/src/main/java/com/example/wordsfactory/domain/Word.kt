package com.example.wordsfactory.domain

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
class Word {
    val word: String
    val phoneticAudioSrc: String?
    val phoneticText: String?
    val partOfSpeech: String
    val definitions: ArrayList<Definition>?
    val example: String?

    constructor( data: WordRaw ) {
        this.word = data.word.replaceFirstChar( Char::titlecase )
        this.phoneticAudioSrc = data.phonetics?.get(0)?.audio
        this.phoneticText = data.phonetics?.get(0)?.text
        this.partOfSpeech = data.meanings?.get(0)?.partOfSpeech!!.replaceFirstChar( Char::titlecase )
        this.definitions = data.meanings?.get(0)?.definitions
        this.example = data.meanings?.get(0)?.definitions?.get(0)?.example
    }
}
