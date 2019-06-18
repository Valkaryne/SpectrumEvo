package com.epam.valkaryne.spectrumevo.repository.datamodel.spectrumfeatures

import com.epam.valkaryne.spectrumevo.repository.datamodel.spectrumfeatures.Criterion.Companion.CRITERIA_COUNT

data class InformationCriterion(
    var education: Int = 0,
    var riddle: Int = 0,
    var communication: Int = 0,
    var role: Int = 0,
    var exploration: Int = 0
) : Criterion {
    override val criterionValue: Float
        get() = (education + riddle + communication + role + exploration) / CRITERIA_COUNT
}
