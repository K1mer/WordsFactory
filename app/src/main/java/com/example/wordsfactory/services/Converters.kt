package com.example.wordsfactory.services

import androidx.room.TypeConverter
import com.example.wordsfactory.domain.Definition
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class Converters {
    @TypeConverter
    fun fromArrayList( definitions: List<Definition>? ): String? {
        return Gson().toJson( definitions )
    }

    @TypeConverter
    fun fromString( str: String? ): List<Definition>? {
        val listType = object : TypeToken<List<Definition>?>() {}.type
        return Gson().fromJson( str, listType )
    }
}