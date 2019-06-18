package com.epam.valkaryne.spectrumevo.viewmodel

import androidx.lifecycle.ViewModel
import com.epam.valkaryne.spectrumevo.repository.SpectrumRepository

/**
 * [ViewModel] responsible for representing local/network game data as paged list.
 *
 * @author Valentine Litvin
 */
class SpectrumListViewModel(private val repository: SpectrumRepository) : ViewModel() {

    var networkGamesList = repository.getGames()
        private set

    var localGamesList = repository.getGamesFromLocal()
        private set

    fun refresh() {
        repository.refresh()
    }
}