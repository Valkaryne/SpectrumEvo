package com.epam.valkaryne.spectrumevo.repository.datamodel.converters

import androidx.room.TypeConverter
import com.epam.valkaryne.spectrumevo.repository.datamodel.Genre
import com.google.gson.Gson

/**
 * Converter to get JSON string from genres array and visa-versa.
 *
 * @author Valentine Litvin
 */
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