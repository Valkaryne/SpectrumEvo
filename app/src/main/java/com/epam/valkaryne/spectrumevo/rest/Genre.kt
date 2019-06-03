package com.epam.valkaryne.spectrumevo.rest

data class Genre(
    val id: Int = 0,
    val name: String = ""
) {
    override fun toString(): String {
        return name
    }
}