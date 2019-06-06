package com.epam.valkaryne.spectrumevo.repository.network.api

import com.epam.valkaryne.spectrumevo.repository.datamodel.Game
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

/**
 * Interface for communication with IGDB API.
 *
 * @author Valentine Litvin
 */
interface IGDBService {

    @POST("/games/")
    fun getGames(
        @Header("user-key") key: String,
        @Body body: RequestBody
    ): Call<List<Game>>

    companion object {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api-v3.igdb.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}