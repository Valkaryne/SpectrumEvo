package com.epam.valkaryne.spectrumevo.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.epam.valkaryne.spectrumevo.R
import com.epam.valkaryne.spectrumevo.adapter.GamesCardAdapter
import com.epam.valkaryne.spectrumevo.adapter.ViewPagerTransformer
import com.epam.valkaryne.spectrumevo.listeners.ItemClickListener
import com.epam.valkaryne.spectrumevo.repository.datamodel.Game
import com.epam.valkaryne.spectrumevo.viewmodel.SpectrumDetailsViewModel
import com.epam.valkaryne.spectrumevo.viewmodel.SpectrumListViewModel

class SpectrumPageFragment : Fragment(), ItemClickListener {

    private lateinit var listViewModel: SpectrumListViewModel
    private lateinit var detailsViewModel: SpectrumDetailsViewModel
    private lateinit var viewPager: ViewPager
    private val adapter = GamesCardAdapter(this)
    private lateinit var cardShadowTransformer: ViewPagerTransformer

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_spectrum_page, container, false)
        viewPager = view.findViewById(R.id.view_pager)

        listViewModel = ViewModelProviders.of(activity!!).get(SpectrumListViewModel::class.java)
        viewPager.adapter = adapter
        viewPager.offscreenPageLimit = 3

        cardShadowTransformer = ViewPagerTransformer(viewPager, adapter)
        viewPager.setPageTransformer(false, cardShadowTransformer)

        registerObservers()

        return view
    }

    private fun registerObservers() {
        listViewModel.gamesListLocal?.observe(this,
            Observer<List<Game>> { list ->
                adapter.submitList(list)
                if (list.isNotEmpty()) cardShadowTransformer.enableScaling(true)
            })
        detailsViewModel =
            ViewModelProviders.of(activity!!).get(SpectrumDetailsViewModel::class.java)
    }

    override fun onItemClick(game: Game) {
        detailsViewModel.game.postValue(game)
        if (!detailsViewModel.game.hasActiveObservers()) {
            val detailsFragment = SpectrumAdvancedDetailsFragment()
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.fragments_container, detailsFragment)?.addToBackStack(null)?.commit()
        }
    }
}