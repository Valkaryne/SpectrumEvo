package com.epam.valkaryne.spectrumevo.repository.datamodel.spectrumfeatures

import com.epam.valkaryne.spectrumevo.repository.datamodel.spectrumfeatures.Criterion.Companion.CRITERIA_COUNT

data class ControlCriterion(
    var care: Int,
    var building: Int,
    var microcontrol: Int,
    var tactic: Int,
    var plan: Int
) : Criterion {
    override val criterionValue: Float
        get() = (care + building + microcontrol + tactic + plan) / CRITERIA_COUNT
}