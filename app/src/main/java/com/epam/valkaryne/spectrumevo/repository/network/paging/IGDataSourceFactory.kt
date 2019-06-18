package com.epam.valkaryne.spectrumevo.repository.network.paging

import androidx.paging.DataSource
import com.epam.valkaryne.spectrumevo.repository.datamodel.Game

/**
 * Factory creates DataSource instance.
 *
 * @author Valentine Litvin
 */
class IGDataSourceFactory : DataSource.Factory<String, Game>() {

    private val igdbDataSource = IGDataSource()

    override fun create(): DataSource<String, Game> {
        return igdbDataSource
    }

    fun refresh() {
        igdbDataSource.refreshData()
    }
}