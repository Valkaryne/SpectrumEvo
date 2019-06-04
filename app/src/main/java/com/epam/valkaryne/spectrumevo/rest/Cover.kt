package com.epam.valkaryne.spectrumevo.rest

import com.google.gson.annotations.SerializedName

data class Cover(
    val id: Int = 0,
    @SerializedName("image_id")
    val url: String = ""
)