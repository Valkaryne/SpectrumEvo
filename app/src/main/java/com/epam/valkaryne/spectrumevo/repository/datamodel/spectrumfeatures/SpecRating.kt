package com.epam.valkaryne.spectrumevo.repository.datamodel.spectrumfeatures

data class SpecRating(
    var facade: Int = 0,
    var mechanics: Int = 0,
    var content: Int = 0
) {
    val commonRating: Float
        get() = (facade + mechanics + content) / RATING_COUNT

    private companion object {
        const val RATING_COUNT = 3F
    }
}