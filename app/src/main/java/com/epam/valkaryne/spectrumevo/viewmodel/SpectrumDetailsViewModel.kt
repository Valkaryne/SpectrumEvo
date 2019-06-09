package com.epam.valkaryne.spectrumevo.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.epam.valkaryne.spectrumevo.repository.SpectrumRepository
import com.epam.valkaryne.spectrumevo.repository.datamodel.Game

class SpectrumDetailsViewModel(application: Application) : AndroidViewModel(application) {

    val repository = SpectrumRepository.getInstance(application)

    var game: MutableLiveData<Game> = MutableLiveData()
        private set

    init {
        Log.d("SpectrumDetailsVM", "Meow")
    }

    fun insert() {
        game.value?.let { repository?.insertGameIntoRoom(it)
            Log.i("SuperCat", "Insert ${it.title}")}
    }

    fun delete() {
        game.value?.let { repository?.deleteGameFromRoom(it)
            Log.i("SuperCat", "Delete ${it.title}")}
    }
}