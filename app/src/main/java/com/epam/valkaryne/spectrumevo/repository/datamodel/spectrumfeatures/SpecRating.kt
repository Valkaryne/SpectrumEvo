package com.epam.valkaryne.spectrumevo.repository.datamodel.spectrumfeatures

data class SpecRating(
    var facade: Int,
    var mechanics: Int,
    var content: Int
) {
    val commonRating: Float
        get() = (facade + mechanics + content) / 3F
}