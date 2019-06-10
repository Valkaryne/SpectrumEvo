package com.epam.valkaryne.spectrumevo.repository.datamodel.spectrumfeatures

data class ActionCriterion(
    val collecting: Int,
    val avoidance: Int,
    val destruction: Int,
    val competition: Int,
    val simulation: Int
) : Criterion {
    override val criterionValue: Float
        get() = (collecting + avoidance + destruction + competition + simulation) / 5F
}