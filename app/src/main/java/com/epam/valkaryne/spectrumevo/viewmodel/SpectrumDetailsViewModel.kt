package com.epam.valkaryne.spectrumevo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.epam.valkaryne.spectrumevo.repository.datamodel.Game

class SpectrumDetailsViewModel : ViewModel() {

    var game: MutableLiveData<Game> = MutableLiveData()
        private set
}