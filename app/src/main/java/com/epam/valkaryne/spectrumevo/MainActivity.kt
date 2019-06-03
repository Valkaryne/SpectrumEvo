package com.epam.valkaryne.spectrumevo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.epam.valkaryne.spectrumevo.adapter.GamesAdapter
import com.epam.valkaryne.spectrumevo.rest.ApiSuccessResponse
import com.epam.valkaryne.spectrumevo.rest.Game
import com.epam.valkaryne.spectrumevo.rest.IGDBService
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Body

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProviders.of(this).get(SpectrumViewModel::class.java)

        val gamesRecycler = findViewById<RecyclerView>(R.id.recycler_games)
        gamesRecycler.adapter = viewModel.adapter
        gamesRecycler.layoutManager = LinearLayoutManager(this)

        viewModel.requestPage(1)
    }
}
