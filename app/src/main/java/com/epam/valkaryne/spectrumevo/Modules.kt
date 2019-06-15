package com.epam.valkaryne.spectrumevo

import androidx.paging.PagedList
import androidx.room.Room
import com.epam.valkaryne.spectrumevo.callbacks.NetworkBoundaryCallback
import com.epam.valkaryne.spectrumevo.repository.SpectrumRepository
import com.epam.valkaryne.spectrumevo.repository.datamodel.Game
import com.epam.valkaryne.spectrumevo.repository.network.SpectrumNetwork
import com.epam.valkaryne.spectrumevo.repository.room.SpectrumDatabase
import com.epam.valkaryne.spectrumevo.viewmodel.SpectrumDetailsViewModel
import com.epam.valkaryne.spectrumevo.viewmodel.SpectrumListViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainAppModule = module {

    // Callbacks
    factory<PagedList.BoundaryCallback<Game>> { NetworkBoundaryCallback() }

    // Repository
    single {
        Room.databaseBuilder(androidApplication(), SpectrumDatabase::class.java, "Spectrum").build()
    }
    single { SpectrumNetwork(get()) }
    single { SpectrumRepository(get(), get()) }

    // ViewModels
    viewModel { SpectrumListViewModel(get()) }
    viewModel { SpectrumDetailsViewModel(get()) }
}
