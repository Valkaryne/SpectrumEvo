package com.epam.valkaryne.spectrumevo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.epam.valkaryne.spectrumevo.adapter.GamesPageListAdapter
import com.epam.valkaryne.spectrumevo.repository.datamodel.Game
import com.epam.valkaryne.spectrumevo.viewmodel.SpectrumListViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gamesRecycler = findViewById<RecyclerView>(R.id.recycler_games)
        gamesRecycler.layoutManager = LinearLayoutManager(baseContext)

        val viewModel = ViewModelProviders.of(this).get(SpectrumListViewModel::class.java)
        val pageListAdapter = GamesPageListAdapter()
        gamesRecycler.adapter = pageListAdapter

        val observer = Observer<PagedList<Game>> {
            pageListAdapter.submitList(it)
        }
        viewModel.gamesList?.observe(this, observer)
    }
}
