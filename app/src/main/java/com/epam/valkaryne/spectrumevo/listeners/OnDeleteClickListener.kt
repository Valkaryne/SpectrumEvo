package com.epam.valkaryne.spectrumevo.listeners

import com.epam.valkaryne.spectrumevo.repository.datamodel.Game

/**
 * Listener on click-on-delete event.
 *
 * @author Valentine Litvin
 */
interface OnDeleteClickListener {
    fun onDeleteClick(game: Game)
}