package com.epam.valkaryne.spectrumevo.repository.datamodel.spectrumfeatures

data class InformationCriterion(
    var education: Int,
    var riddle: Int,
    var communication: Int,
    var role: Int,
    var exploration: Int
) : Criterion {
    override val criterionValue: Float
        get() = (education + riddle + communication + role + exploration) / 5F
}
