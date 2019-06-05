package com.epam.valkaryne.spectrumevo.rest

import com.google.gson.annotations.SerializedName

data class Game(
    val id: Int,
    @SerializedName("name")
    val title: String = "",
    var cover: Cover,
    var genres: List<Genre>,
    @SerializedName("total_rating")
    val rating: Double = 0.0
)