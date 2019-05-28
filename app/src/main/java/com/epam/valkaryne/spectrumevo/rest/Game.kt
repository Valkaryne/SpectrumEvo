package com.epam.valkaryne.spectrumevo.rest

import com.google.gson.annotations.SerializedName

data class Game(
        @SerializedName("name")
        val title: String = ""
)