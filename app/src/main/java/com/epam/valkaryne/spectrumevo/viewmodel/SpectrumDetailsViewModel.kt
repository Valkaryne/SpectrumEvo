package com.epam.valkaryne.spectrumevo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.epam.valkaryne.spectrumevo.repository.SpectrumRepository
import com.epam.valkaryne.spectrumevo.repository.datamodel.Game

class SpectrumDetailsViewModel(private val repository: SpectrumRepository) : ViewModel() {

    var game: MutableLiveData<Game> = MutableLiveData()
        private set

    fun insert() {
        game.value?.let {
            repository.insertGameIntoRoom(it)
        }
    }

    fun delete() {
        game.value?.let {
            repository.deleteGameFromRoom(it)
        }
    }
}