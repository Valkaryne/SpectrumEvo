package com.epam.valkaryne.spectrumevo.repository.network.paging

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.epam.valkaryne.spectrumevo.MAX_ITEMS_ON_PAGE
import com.epam.valkaryne.spectrumevo.repository.datamodel.Game
import com.epam.valkaryne.spectrumevo.repository.network.api.IGDBService
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Class describes receiving game data from API service page by page.
 *
 * @author Valentine Litvin
 */
class IGDataSource : PageKeyedDataSource<String, Game>() {

    private val igdbService = IGDBService.retrofit.create(IGDBService::class.java)
    private val igdbData = MutableLiveData<List<Game>>()

    override fun loadInitial(
        loadParams: LoadInitialParams<String>,
        loadCallback: LoadInitialCallback<String, Game>
    ) {
        Log.i(TAG, "Load Initial")

        val body = initRequestBody(1)

        val callback = igdbService.getGames(KEY, body)
        callback.enqueue(object : Callback<List<Game>> {
            override fun onFailure(call: Call<List<Game>>, t: Throwable) {
                val errorMessage = if (t.message == null) "unknown error" else t.message
                Log.e(TAG, errorMessage)
                loadCallback.onResult(ArrayList<Game>(), "1", "2")
            }

            override fun onResponse(call: Call<List<Game>>, response: Response<List<Game>>) {
                if (response.isSuccessful) {
                    loadCallback.onResult(response.body()!!.toMutableList(), "1", "2")
                    igdbData.postValue(response.body())
                } else {
                    Log.e(API_TAG, response.message())
                }
            }
        })
    }

    override fun loadAfter(
        loadParams: LoadParams<String>,
        loadCallback: LoadCallback<String, Game>
    ) {
        Log.i(TAG, "Load After")

        val page = loadParams.key.toInt()
        val body = initRequestBody(page)

        val callback = igdbService.getGames(KEY, body)
        callback.enqueue(object : Callback<List<Game>> {
            override fun onFailure(call: Call<List<Game>>, t: Throwable) {
                val errorMessage = if (t.message == null) "unknown error" else t.message
                Log.e(TAG, errorMessage)
                loadCallback.onResult(ArrayList<Game>(), page.toString())
            }

            override fun onResponse(call: Call<List<Game>>, response: Response<List<Game>>) {
                if (response.isSuccessful) {
                    loadCallback.onResult(response.body()!!.toMutableList(), (page + 1).toString())
                    igdbData.postValue(response.body())
                }
            }
        })
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, Game>) {
        Log.i(TAG, "Load Before")
    }

    private fun initRequestBody(page: Int): RequestBody {
        val offset = (page - 1) * MAX_ITEMS_ON_PAGE
        return RequestBody.create(MediaType.parse("text/plain"), String.format(QUERY, offset))
    }

    companion object {
        private val TAG = IGDataSource::class.java.simpleName
        private const val API_TAG = "API"
        private const val KEY = "5b054f2a99970e757793aef72ec608c5"
        private const val QUERY =
            """fields name, cover.image_id, genres.name, total_rating, total_rating_count,
            involved_companies.company.name, involved_companies.developer, first_release_date, summary;
            limit $MAX_ITEMS_ON_PAGE;
            offset %2d;
            where total_rating > 50 & total_rating_count > 275;"""
    }
}
