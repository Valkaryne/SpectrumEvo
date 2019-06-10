package com.epam.valkaryne.spectrumevo.repository.datamodel.spectrumfeatures

data class SpecRating(
    val facade: Int,
    val mechanics: Int,
    val content: Int
) {
    val commonRating: Float
        get() = (facade + mechanics + content) / 3F
}