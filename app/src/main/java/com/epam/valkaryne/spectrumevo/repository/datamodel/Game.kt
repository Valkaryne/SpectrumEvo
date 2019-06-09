package com.epam.valkaryne.spectrumevo.repository.datamodel

import androidx.room.*
import com.epam.valkaryne.spectrumevo.repository.datamodel.converters.GenresConverter
import com.epam.valkaryne.spectrumevo.repository.datamodel.converters.InvolvedCompaniesConverter
import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author Valentine Litvin
 */
@Entity(tableName = "games")
@TypeConverters(GenresConverter::class, InvolvedCompaniesConverter::class)
data class Game(

    @PrimaryKey val id: Int,
    @SerializedName("name") val title: String,
    @Embedded(prefix = "cover_") val cover: Cover,
    val genres: List<Genre>,
    @SerializedName("total_rating") val rating: Double,
    @ColumnInfo(name = "rating_count") @SerializedName("total_rating_count") val ratingCount: Int,
    @ColumnInfo(name = "release_date") @SerializedName("first_release_date") val releaseDate: Long,
    @SerializedName("involved_companies") val involvedCompanies: List<InvolvedCompany>
) {
    fun getDeveloper(): Company? {
        val involvedCompanies = involvedCompanies
        involvedCompanies.forEach { if (it.developer) return it.company }
        return null
    }

    fun getReleaseYear(): String {
        val timestamp = releaseDate
        val date = Date(timestamp * 1000L)
        val dateFormat = SimpleDateFormat("yyyy")
        return dateFormat.format(date)
    }
}