package com.epam.valkaryne.spectrumevo.viewmodel

import androidx.lifecycle.ViewModel
import com.epam.valkaryne.spectrumevo.repository.SpectrumRepository

/**
 * [ViewModel] responsible for representing local/network game data as paged list.
 *
 * @author Valentine Litvin
 */
class SpectrumListViewModel : ViewModel() {

    private val repository = SpectrumRepository.getInstance()
    var gamesList = repository?.getGames()
        private set
}