package com.epam.valkaryne.spectrumevo

import androidx.lifecycle.ViewModel
import com.epam.valkaryne.spectrumevo.adapter.GamesAdapter
import com.epam.valkaryne.spectrumevo.rest.ApiSuccessResponse
import com.epam.valkaryne.spectrumevo.rest.IGDBService
import okhttp3.MediaType
import okhttp3.RequestBody

class SpectrumViewModel: ViewModel() {

    val adapter: GamesAdapter = GamesAdapter()

    private val igdbService = IGDBService.retrofit.create(IGDBService::class.java)
    private val key = "5b054f2a99970e757793aef72ec608c5"

    private val maxItemsOnPage = 15

    fun requestPage(page: Int)/*: LiveData<ApiResponse<List<Game>>> */{
        val offset = (page - 1) * maxItemsOnPage
        val query = """fields name, cover.image_id, genres.name, total_rating;
            limit $maxItemsOnPage;
            offset $offset;
            where total_rating > 50 & total_rating_count > 275;"""
        val body = RequestBody.create(MediaType.parse("text/plain"), query)

        igdbService.getGames(key, body).observeForever { response ->
            if (response is ApiSuccessResponse) {
                adapter.addGames(response.body)
            }
        }
    }
}