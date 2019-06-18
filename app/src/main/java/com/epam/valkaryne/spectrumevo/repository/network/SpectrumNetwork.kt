package com.epam.valkaryne.spectrumevo.repository.network

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.epam.valkaryne.spectrumevo.MAX_ITEMS_ON_PAGE
import com.epam.valkaryne.spectrumevo.repository.datamodel.Game
import com.epam.valkaryne.spectrumevo.repository.network.paging.IGDataSourceFactory
import java.util.concurrent.Executors

/**
 * Represents network part of the application repository.
 *
 * @author Valentine Litvin
 */
class SpectrumNetwork(
    boundaryCallback: PagedList.BoundaryCallback<Game>
) {

    var gamesPaged: LiveData<PagedList<Game>>
        private set

    private val dataSourceFactory = IGDataSourceFactory()

    init {
        val pagedListConfig = (PagedList.Config.Builder()).setEnablePlaceholders(false)
            .setInitialLoadSizeHint(MAX_ITEMS_ON_PAGE).setPageSize(MAX_ITEMS_ON_PAGE).build()
        val executor = Executors.newFixedThreadPool(2)
        val livePagedListBuilder = LivePagedListBuilder(dataSourceFactory, pagedListConfig)
        gamesPaged = livePagedListBuilder
            .setFetchExecutor(executor)
            .setBoundaryCallback(boundaryCallback)
            .build()
    }

    fun refresh() {
        dataSourceFactory.refresh()
    }
}