package com.epam.valkaryne.spectrumevo.repository.datamodel.spectrumfeatures

import com.epam.valkaryne.spectrumevo.repository.datamodel.spectrumfeatures.Criterion.Companion.CRITERIA_COUNT

data class ActionCriterion(
    var collecting: Int,
    var avoidance: Int,
    var destruction: Int,
    var competition: Int,
    var simulation: Int
) : Criterion {
    override val criterionValue: Float
        get() = (collecting + avoidance + destruction + competition + simulation) / CRITERIA_COUNT
}