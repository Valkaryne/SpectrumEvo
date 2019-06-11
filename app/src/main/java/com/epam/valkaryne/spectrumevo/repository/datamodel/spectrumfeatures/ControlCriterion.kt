package com.epam.valkaryne.spectrumevo.repository.datamodel.spectrumfeatures

data class ControlCriterion(
    var care: Int,
    var building: Int,
    var microcontrol: Int,
    var tactic: Int,
    var plan: Int
) : Criterion {
    override val criterionValue: Float
        get() = (care + building + microcontrol + tactic + plan) / 5F
}