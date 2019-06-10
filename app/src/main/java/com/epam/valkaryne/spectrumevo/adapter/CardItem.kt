package com.epam.valkaryne.spectrumevo.adapter

import com.epam.valkaryne.spectrumevo.repository.datamodel.Game

class CardItem(val game: Game) {
    val title = game.title
    val coverUrl = game.cover.url
    val rating = game.specRating.commonRating
}