package com.epam.valkaryne.spectrumevo.repository.datamodel.converters

import androidx.room.TypeConverter
import com.epam.valkaryne.spectrumevo.repository.datamodel.Genre

class GenresConverter {

    @TypeConverter
    fun fromGenres(genres: List<Genre>): String {
        return genres.joinToString(separator = ",") { genre -> genre.name }
    }

    @TypeConverter
    fun toGenres(data: String): List<Genre> {
        val arrStr = data.split(",")
        val genres = ArrayList<Genre>()
        arrStr.forEach { genres.add(Genre(it)) }
        return genres
    }
}