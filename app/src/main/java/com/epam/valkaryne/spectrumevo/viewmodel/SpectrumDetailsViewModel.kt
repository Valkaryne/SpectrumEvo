package com.epam.valkaryne.spectrumevo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.epam.valkaryne.spectrumevo.repository.SpectrumRepository
import com.epam.valkaryne.spectrumevo.repository.datamodel.Game

class SpectrumDetailsViewModel(private val repository: SpectrumRepository) : ViewModel() {

    var game: MutableLiveData<Game> = MutableLiveData()
        private set

    fun insert(game: Game) {
        this.game.postValue(game)
        repository.insertGameIntoRoom(game)
    }

    fun delete(game: Game) {
        this.game.postValue(game)
        repository.deleteGameFromRoom(game)
    }
}