package com.epam.valkaryne.spectrumevo.repository.network

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.epam.valkaryne.spectrumevo.repository.datamodel.Game
import com.epam.valkaryne.spectrumevo.repository.network.paging.IGDataSourceFactory
import java.util.concurrent.Executors

/**
 * Represents network part of the application repository.
 *
 * @author Valentine Litvin
 */
class SpectrumNetwork(
    dataSourceFactory: IGDataSourceFactory,
    boundaryCallback: PagedList.BoundaryCallback<Game>
) {

    var gamesPaged: LiveData<PagedList<Game>>
        private set

    init {
        val pagedListConfig = (PagedList.Config.Builder()).setEnablePlaceholders(false)
            .setInitialLoadSizeHint(maxItemsOnPage).setPageSize(maxItemsOnPage).build()
        val executor = Executors.newFixedThreadPool(2)
        val livePagedListBuilder = LivePagedListBuilder(dataSourceFactory, pagedListConfig)
        gamesPaged = livePagedListBuilder
            .setFetchExecutor(executor)
            .setBoundaryCallback(boundaryCallback)
            .build()
    }

    companion object {
        private const val maxItemsOnPage = 15
    }
}