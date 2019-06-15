package com.epam.valkaryne.spectrumevo.viewmodel

import androidx.lifecycle.ViewModel
import com.epam.valkaryne.spectrumevo.repository.SpectrumRepository

/**
 * [ViewModel] responsible for representing local/network game data as paged list.
 *
 * @author Valentine Litvin
 */
class SpectrumListViewModel(repository: SpectrumRepository) : ViewModel() {

    var gamesList = repository.getGames()
        private set

    var gamesListLocal = repository.getGamesFromLocal()
        private set
}