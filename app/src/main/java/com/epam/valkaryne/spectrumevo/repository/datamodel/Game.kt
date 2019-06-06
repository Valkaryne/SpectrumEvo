package com.epam.valkaryne.spectrumevo.repository.datamodel

import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author Valentine Litvin
 */
data class Game(
    val id: Int,
    @SerializedName("name")
    val title: String,
    val cover: Cover,
    val genres: List<Genre>,
    @SerializedName("total_rating")
    val rating: Double,
    @SerializedName("total_rating_count")
    val ratingCount: Int,
    @SerializedName("first_release_date")
    val releaseDate: Long,
    @SerializedName("involved_companies")
    val involvedCompanies: List<InvolvedCompany>
) {
    fun getDeveloper(): Company? {
        val involvedCompanies = involvedCompanies
        involvedCompanies.forEach { if (it.developer) return it.company }
        return null
    }

    fun getReleaseYear(): String {
        val timestamp = releaseDate
        val date = Date(timestamp*1000L)
        val dateFormat = SimpleDateFormat("yyyy")
        return dateFormat.format(date)
    }
}