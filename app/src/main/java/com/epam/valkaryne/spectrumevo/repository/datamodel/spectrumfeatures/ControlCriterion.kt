package com.epam.valkaryne.spectrumevo.repository.datamodel.spectrumfeatures

data class ControlCriterion(
    val care: Int,
    val building: Int,
    val microcontrol: Int,
    val tactic: Int,
    val plan: Int
) : Criterion {
    override val criterionValue: Float
        get() = (care + building + microcontrol + tactic + plan) / 5F
}