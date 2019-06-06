package com.epam.valkaryne.spectrumevo.repository.datamodel

import com.google.gson.annotations.SerializedName

/**
 * @author Valentine Litvin
 */
data class Cover(
    @SerializedName("image_id")
    val url: String = ""
)