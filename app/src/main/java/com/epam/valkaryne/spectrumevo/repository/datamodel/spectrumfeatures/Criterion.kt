package com.epam.valkaryne.spectrumevo.repository.datamodel.spectrumfeatures

/**
 * Interface describes any criteria of a game.
 *
 * @author Valentine Litvin
 */
interface Criterion {
    val criterionValue: Float

    companion object {
        const val CRITERIA_COUNT = 5F
    }
}