package com.epam.valkaryne.spectrumevo.rest

import androidx.lifecycle.LiveData
import com.epam.valkaryne.spectrumevo.adapter.LiveDataCallAdapterFactory
import okhttp3.RequestBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface IGDBService {

    @POST("/games/")
    fun getGames(
        @Header("user-key") key: String,
        @Body body: RequestBody
    ): LiveData<ApiResponse<List<Game>>>

    @POST("/covers/")
    fun getCover(
        @Header("user-key") key: String,
        @Body body: RequestBody
    ): LiveData<ApiResponse<List<Cover>>>

    @POST("/genres/")
    fun getGenres(
        @Header("user-key") key: String,
        @Body body: RequestBody
    ): LiveData<ApiResponse<List<Genre>>>

    companion object {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api-v3.igdb.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
    }
}