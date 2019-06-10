package com.epam.valkaryne.spectrumevo.repository.datamodel.spectrumfeatures

data class InformationCriterion(
    val education: Int,
    val riddle: Int,
    val communication: Int,
    val role: Int,
    val exploration: Int
) : Criterion {
    override val criterionValue: Float
        get() = (education + riddle + communication + role + exploration) / 5F
}
