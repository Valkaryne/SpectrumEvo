package com.epam.valkaryne.spectrumevo.callbacks

import android.util.Log
import androidx.paging.PagedList
import com.epam.valkaryne.spectrumevo.repository.datamodel.Game

class NetworkBoundaryCallback : PagedList.BoundaryCallback<Game>() {

    override fun onZeroItemsLoaded() {
        super.onZeroItemsLoaded()
        Log.i(TAG, "Zero Items Loaded")
    }

    private companion object {
        val TAG: String = NetworkBoundaryCallback::class.java.simpleName
    }
}