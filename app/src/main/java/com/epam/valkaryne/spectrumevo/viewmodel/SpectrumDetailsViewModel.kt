package com.epam.valkaryne.spectrumevo.viewmodel


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.epam.valkaryne.spectrumevo.repository.SpectrumRepository
import com.epam.valkaryne.spectrumevo.repository.datamodel.Game

class SpectrumDetailsViewModel(application: Application) : AndroidViewModel(application) {

    val repository = SpectrumRepository.getInstance(application)

    var game: MutableLiveData<Game> = MutableLiveData()
        private set

    fun insert() {
        game.value?.let {
            repository?.insertGameIntoRoom(it)
        }
    }

    fun delete() {
        game.value?.let {
            repository?.deleteGameFromRoom(it)
        }
    }
}