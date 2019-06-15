package com.epam.valkaryne.spectrumevo.listeners

import com.epam.valkaryne.spectrumevo.repository.datamodel.Game

interface OnDeleteClickListener {
    fun onDeleteClick(game: Game)
}