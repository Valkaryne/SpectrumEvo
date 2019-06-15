package com.epam.valkaryne.spectrumevo.repository.datamodel.converters

import androidx.room.TypeConverter
import com.epam.valkaryne.spectrumevo.repository.datamodel.Genre
import com.google.gson.Gson

class GenresConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromGenres(genres: List<Genre>): String {
        return gson.toJson(genres)
    }

    @TypeConverter
    fun toGenres(data: String): List<Genre> {
        return gson.fromJson(data, Array<Genre>::class.java).toList()
    }
}