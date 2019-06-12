package com.epam.valkaryne.spectrumevo.repository.datamodel

import androidx.room.Entity
import androidx.room.TypeConverters
import androidx.room.PrimaryKey
import androidx.room.Embedded
import androidx.room.ColumnInfo
import com.epam.valkaryne.spectrumevo.repository.datamodel.converters.GenresConverter
import com.epam.valkaryne.spectrumevo.repository.datamodel.converters.InvolvedCompaniesConverter
import com.epam.valkaryne.spectrumevo.repository.datamodel.spectrumfeatures.ActionCriterion
import com.epam.valkaryne.spectrumevo.repository.datamodel.spectrumfeatures.ControlCriterion
import com.epam.valkaryne.spectrumevo.repository.datamodel.spectrumfeatures.InformationCriterion
import com.epam.valkaryne.spectrumevo.repository.datamodel.spectrumfeatures.SpecRating
import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.Date

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
    val summary: String,
    @SerializedName("total_rating") val rating: Double,
    @ColumnInfo(name = "rating_count") @SerializedName("total_rating_count") val ratingCount: Int,
    @ColumnInfo(name = "release_date") @SerializedName("first_release_date") val releaseDate: Long,
    @SerializedName("involved_companies") val involvedCompanies: List<InvolvedCompany>,
    @Transient @Embedded(prefix = "info_") var informationCriterion: InformationCriterion,
    @Transient @Embedded(prefix = "action_") var actionCriterion: ActionCriterion,
    @Transient @Embedded(prefix = "control_") var controlCriterion: ControlCriterion,
    @Transient @Embedded(prefix = "common_") var specRating: SpecRating
) {
    fun getDeveloper(): Company? {
        val involvedCompanies = involvedCompanies
        involvedCompanies.forEach { if (it.developer) return it.company }
        return null
    }

    fun getReleaseYear(): String {
        val timestamp = releaseDate
        val date = Date(timestamp * TIMESTAMP_MULTIPLIER)
        val dateFormat = SimpleDateFormat("yyyy")
        return dateFormat.format(date)
    }

    fun getTenScaledRating() = rating / RATING_SCALER

    private companion object {
        const val TIMESTAMP_MULTIPLIER = 1000L
        const val RATING_SCALER = 10
    }
}