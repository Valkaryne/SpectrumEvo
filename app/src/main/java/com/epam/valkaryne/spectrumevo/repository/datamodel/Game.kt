package com.epam.valkaryne.spectrumevo.repository.datamodel

import com.google.gson.annotations.SerializedName

/**
 * @author Valentine Litvin
 */
data class Game(
    val id: Int,
    @SerializedName("name")
    val title: String = "",
    val cover: Cover,
    val genres: List<Genre>,
    @SerializedName("total_rating")
    val rating: Double = 0.0
)