package com.epam.valkaryne.spectrumevo.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager
import com.epam.valkaryne.spectrumevo.R
import com.epam.valkaryne.spectrumevo.adapter.GamesCardAdapter
import com.epam.valkaryne.spectrumevo.adapter.ViewPagerTransformer
import com.epam.valkaryne.spectrumevo.listeners.ItemClickListener
import com.epam.valkaryne.spectrumevo.listeners.OnDeleteClickListener
import com.epam.valkaryne.spectrumevo.repository.datamodel.Game
import com.epam.valkaryne.spectrumevo.viewmodel.SpectrumDetailsViewModel
import com.epam.valkaryne.spectrumevo.viewmodel.SpectrumListViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SpectrumPageFragment : Fragment(), ItemClickListener {

    private val onDeleteClickListener = object : OnDeleteClickListener {
        override fun onDeleteClick(game: Game) {
            detailsViewModel.delete(game)
        }
    }

    private val listViewModel: SpectrumListViewModel by sharedViewModel()
    private val detailsViewModel: SpectrumDetailsViewModel by sharedViewModel()
    private lateinit var viewPager: ViewPager
    private val adapter = GamesCardAdapter(this, onDeleteClickListener)
    private lateinit var cardShadowTransformer: ViewPagerTransformer

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_spectrum_page, container, false)
        viewPager = view.findViewById(R.id.view_pager)

        viewPager.adapter = adapter
        viewPager.offscreenPageLimit = STANDARD_OFFSCREEN_PAGES_LIMIT

        cardShadowTransformer = ViewPagerTransformer(viewPager, adapter)
        viewPager.setPageTransformer(false, cardShadowTransformer)

        registerObservers()

        return view
    }

    private fun registerObservers() {
        listViewModel.localGamesList.observe(this,
            Observer<List<Game>> { list ->
                viewPager.adapter = adapter
                adapter.submitList(list)
                if (list.isNotEmpty()) cardShadowTransformer.enableScaling(true)
            })
    }

    override fun onItemClick(game: Game) {
        detailsViewModel.game.postValue(game)
        if (!detailsViewModel.game.hasActiveObservers()) {
            val detailsFragment = SpectrumAdvancedDetailsFragment()
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.fragments_container, detailsFragment)?.addToBackStack(null)?.commit()
        }
    }

    private companion object {
        const val STANDARD_OFFSCREEN_PAGES_LIMIT = 3
    }
}