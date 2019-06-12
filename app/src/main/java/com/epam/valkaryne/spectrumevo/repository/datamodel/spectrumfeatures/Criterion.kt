package com.epam.valkaryne.spectrumevo.repository.datamodel.spectrumfeatures

interface Criterion {
    val criterionValue: Float

    companion object {
        const val CRITERIA_COUNT = 5F
    }
}