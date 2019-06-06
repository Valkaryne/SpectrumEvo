package com.epam.valkaryne.spectrumevo.repository.datamodel

/**
 * @author Valentine Litvin
 */
data class Genre(
    val name: String = ""
) {
    override fun toString(): String {
        return name
    }
}