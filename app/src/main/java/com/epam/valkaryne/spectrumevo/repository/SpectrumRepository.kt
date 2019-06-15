package com.epam.valkaryne.spectrumevo.repository

import android.os.Handler
import android.os.Looper
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
class SpectrumRepository(network: SpectrumNetwork, private val database: SpectrumDatabase) {

    private val liveDataMerger: MediatorLiveData<PagedList<Game>> = MediatorLiveData()
    private val liveDataLocal: MutableLiveData<List<Game>> = MutableLiveData()

    init {
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
}