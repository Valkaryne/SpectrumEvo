package com.epam.valkaryne.spectrumevo.repository.datamodel.spectrumfeatures

import com.epam.valkaryne.spectrumevo.repository.datamodel.spectrumfeatures.Criterion.Companion.CRITERIA_COUNT

data class ControlCriterion(
    var care: Int = 0,
    var building: Int = 0,
    var microcontrol: Int = 0,
    var tactic: Int = 0,
    var plan: Int = 0
) : Criterion {
    override val criterionValue: Float
        get() = (care + building + microcontrol + tactic + plan) / CRITERIA_COUNT
}