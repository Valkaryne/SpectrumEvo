package com.epam.valkaryne.spectrumevo.repository

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.epam.valkaryne.spectrumevo.repository.datamodel.Game
import com.epam.valkaryne.spectrumevo.repository.network.SpectrumNetwork
import com.epam.valkaryne.spectrumevo.repository.room.SpectrumDatabase

/**
 * Repository provides application with certain means of getting data.
 *
 * @author Valentine Litvin
 */
class SpectrumRepository private constructor(context: Context) {

    private val network: SpectrumNetwork
    private val database: SpectrumDatabase
    private val liveDataMerger: MediatorLiveData<PagedList<Game>> = MediatorLiveData()
    private val liveDataLocal: MutableLiveData<List<Game>> = MutableLiveData()

    private val boundaryCallback = object : PagedList.BoundaryCallback<Game>() {
        override fun onZeroItemsLoaded() {
            super.onZeroItemsLoaded()
            Log.i(TAG, "Zero Items Loaded")
        }
    }

    init {
        network = SpectrumNetwork(boundaryCallback)
        database = SpectrumDatabase.getInstance(context.applicationContext)!!

        Thread { fetchGamesFromDatabase() }.start()

        liveDataMerger.addSource(network.gamesPaged) { value ->
            liveDataMerger.value = value
        }
    }

    fun getGames(): LiveData<PagedList<Game>> = liveDataMerger

    fun getGamesFromLocal(): LiveData<List<Game>> = liveDataLocal

    fun insertGameIntoRoom(game: Game) {
        Thread {
            database.spectrumDao().insertGame(game)
            fetchGamesFromDatabase()
        }.start()
    }

    fun deleteGameFromRoom(game: Game) {
        Thread {
            database.spectrumDao().deleteGame(game)
            fetchGamesFromDatabase()
        }.start()
    }

    private fun fetchGamesFromDatabase() {
        val games: List<Game> = database.spectrumDao().getGames()
        Handler(Looper.getMainLooper()).post { liveDataLocal.value = games }
    }

    companion object {
        private val TAG = SpectrumRepository::class.java.simpleName
        private var instance: SpectrumRepository? = null
        fun getInstance(context: Context): SpectrumRepository? {
            if (instance == null) {
                instance = SpectrumRepository(context)
            }
            return instance
        }
    }
}