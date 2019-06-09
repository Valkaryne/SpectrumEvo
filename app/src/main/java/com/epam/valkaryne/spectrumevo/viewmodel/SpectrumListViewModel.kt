package com.epam.valkaryne.spectrumevo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.epam.valkaryne.spectrumevo.repository.SpectrumRepository

/**
 * [ViewModel] responsible for representing local/network game data as paged list.
 *
 * @author Valentine Litvin
 */
class SpectrumListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = SpectrumRepository.getInstance(application)
    var gamesList = repository?.getGames()
        private set

    var gamesListLocal = repository?.getGamesFromLocal()
        private set
}