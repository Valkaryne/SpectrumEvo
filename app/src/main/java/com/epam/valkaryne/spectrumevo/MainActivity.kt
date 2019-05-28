package com.epam.valkaryne.spectrumevo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.epam.valkaryne.spectrumevo.adapter.GamesAdapter
import com.epam.valkaryne.spectrumevo.rest.Game
import com.epam.valkaryne.spectrumevo.rest.IGDBService
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Body

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: GamesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gamesRecycler = findViewById<RecyclerView>(R.id.recycler_games)
        adapter = GamesAdapter(getGamesFromApi())
        gamesRecycler.adapter = adapter
        gamesRecycler.layoutManager = LinearLayoutManager(this)
    }

    private fun getGamesFromApi(): List<Game> {
        val gamesList: MutableList<Game> = ArrayList()

        val key = "key"
        val query = "fields name;where total_rating > 93 & genres = 12;"
        val body = RequestBody.create(MediaType.parse("text/plain"), query)

        val service = IGDBService.retrofit.create(IGDBService::class.java)
        val call = service.getGames(key, body)

        call.enqueue(object : Callback<List<Game>> {
            override fun onResponse(call: Call<List<Game>>, response: Response<List<Game>>) {
                if (response.isSuccessful) {
                    response.body()?.let { items ->
                        gamesList.addAll(items)
                        adapter.notifyDataSetChanged()
                    }
                } else {
                }
            }

            override fun onFailure(call: Call<List<Game>>, t: Throwable) {
            }
        })

        return gamesList
    }
}
