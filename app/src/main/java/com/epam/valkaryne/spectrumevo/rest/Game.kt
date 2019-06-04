package com.epam.valkaryne.spectrumevo.rest

import androidx.lifecycle.LiveData
import com.google.gson.annotations.SerializedName
import okhttp3.MediaType
import okhttp3.RequestBody

data class Game(
    @SerializedName("name")
    val title: String = "",
    @Transient
    var cover: Cover = Cover(),
    @Transient
    var genres: List<Genre> = ArrayList(),
    @SerializedName("total_rating")
    val rating: Double = 0.0
) {

    @SerializedName("cover")
    private var coverId: Int = 0

    @SerializedName("genres")
    private var genresIds: List<Int> = ArrayList()

    fun getCoverFromApi(key: String, service: IGDBService): LiveData<ApiResponse<List<Cover>>> {
        val query = """fields id, image_id;
            where id = $coverId;"""
        val body = RequestBody.create(MediaType.parse("text/plain"), query)

        return service.getCover(key, body)
    }

    fun getGenresFromApi(key: String, service: IGDBService): LiveData<ApiResponse<List<Genre>>> {
        var queryIds = "where id = ${genresIds[0]}"
        if (genresIds.size > 1) {
            val maxSize = if (genresIds.size > 3) 3 else genresIds.size
            for (i in 1 until maxSize) {
                queryIds = queryIds.plus(" | id = ${genresIds[i]}")
            }
        }
        val query = """fields id, name;
            $queryIds;"""
        val body = RequestBody.create(MediaType.parse("text/plain"), query)

        return service.getGenres(key, body)
    }

    override fun toString(): String {
        return "Game: \n$title \n$cover \n$genresIds"
    }
}