package com.epam.valkaryne.spectrumevo.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.paging.PagedList
import com.epam.valkaryne.spectrumevo.repository.datamodel.Game
import com.epam.valkaryne.spectrumevo.repository.network.SpectrumNetwork
import com.epam.valkaryne.spectrumevo.repository.network.paging.IGDataSourceFactory

/**
 * Repository provides application with certain means of getting data.
 *
 * @author Valentine Litvin
 */
class SpectrumRepository private constructor() {

    private val network: SpectrumNetwork
    private val liveDataMerger: MediatorLiveData<PagedList<Game>>

    private val boundaryCallback = object : PagedList.BoundaryCallback<Game>() {
        override fun onZeroItemsLoaded() {
            super.onZeroItemsLoaded()
            Log.i(TAG, "Zero Items Loaded")
        }
    }

    init {
        val dataSourceFactory = IGDataSourceFactory()
        network = SpectrumNetwork(dataSourceFactory, boundaryCallback)

        liveDataMerger = MediatorLiveData()
        liveDataMerger.addSource(network.gamesPaged) { value ->
            liveDataMerger.value = value
            Log.d(TAG, value.toString())
        }
    }

    fun getGames(): LiveData<PagedList<Game>> = liveDataMerger

    companion object {
        private val TAG = SpectrumNetwork::class.java.simpleName
        private var instance: SpectrumRepository? = null
        fun getInstance(): SpectrumRepository? {
            if (instance == null) {
                instance = SpectrumRepository()
            }
            return instance
        }
    }
}