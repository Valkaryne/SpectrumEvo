package com.epam.valkaryne.spectrumevo.repository.datamodel.spectrumfeatures

import com.epam.valkaryne.spectrumevo.repository.datamodel.spectrumfeatures.Criterion.Companion.CRITERIA_COUNT

data class InformationCriterion(
    var education: Int,
    var riddle: Int,
    var communication: Int,
    var role: Int,
    var exploration: Int
) : Criterion {
    override val criterionValue: Float
        get() = (education + riddle + communication + role + exploration) / CRITERIA_COUNT
}
