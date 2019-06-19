package com.epam.valkaryne.spectrumevo.listeners

import com.epam.valkaryne.spectrumevo.repository.datamodel.Game

/**
 * Listener on click-on-item event.
 *
 * @author Valentine Litvin
 */
interface ItemClickListener {
    fun onItemClick(game: Game)
}