package com.epam.valkaryne.spectrumevo.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.epam.valkaryne.spectrumevo.R
import com.epam.valkaryne.spectrumevo.adapter.GamesPageListAdapter
import com.epam.valkaryne.spectrumevo.listeners.ItemClickListener
import com.epam.valkaryne.spectrumevo.repository.datamodel.Game
import com.epam.valkaryne.spectrumevo.viewmodel.SpectrumDetailsViewModel
import com.epam.valkaryne.spectrumevo.viewmodel.SpectrumListViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SpectrumListFragment : Fragment(), ItemClickListener {

    private val listViewModel: SpectrumListViewModel by sharedViewModel()
    private val detailsViewModel: SpectrumDetailsViewModel by sharedViewModel()
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        recyclerView = view.findViewById(R.id.recycler_games)
        recyclerView.layoutManager = LinearLayoutManager(context)
        registerObservers()
        return view
    }

    private fun registerObservers() {
        val pageListAdapter = GamesPageListAdapter(this)
        listViewModel.gamesList.observe(this,
            Observer<PagedList<Game>> { list -> pageListAdapter.submitList(list) })
        recyclerView.adapter = pageListAdapter
    }

    override fun onItemClick(game: Game) {
        detailsViewModel.game.postValue(game)
        if (!detailsViewModel.game.hasActiveObservers()) {
            val detailsFragment = SpectrumDetailsFragment()
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.fragments_container, detailsFragment)?.addToBackStack(null)?.commit()
        }
    }
}