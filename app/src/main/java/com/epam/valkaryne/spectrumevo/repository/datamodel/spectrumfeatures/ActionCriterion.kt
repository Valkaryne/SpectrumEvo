package com.epam.valkaryne.spectrumevo.repository.datamodel.spectrumfeatures

import com.epam.valkaryne.spectrumevo.repository.datamodel.spectrumfeatures.Criterion.Companion.CRITERIA_COUNT

/**
 * Data class describes action criteria of a game.
 *
 * @author Valentine Litvin
 */
data class ActionCriterion(
    var collecting: Int = 0,
    var avoidance: Int = 0,
    var destruction: Int = 0,
    var competition: Int = 0,
    var simulation: Int = 0
) : Criterion {
    override val criterionValue: Float
        get() = (collecting + avoidance + destruction + competition + simulation) / CRITERIA_COUNT
}