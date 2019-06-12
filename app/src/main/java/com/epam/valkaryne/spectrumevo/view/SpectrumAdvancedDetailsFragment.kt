package com.epam.valkaryne.spectrumevo.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.epam.valkaryne.spectrumevo.R
import com.epam.valkaryne.spectrumevo.repository.datamodel.Game
import com.epam.valkaryne.spectrumevo.view.widgets.BarRatingView
import com.epam.valkaryne.spectrumevo.view.widgets.WebCriteriaView
import com.epam.valkaryne.spectrumevo.viewmodel.SpectrumDetailsViewModel

class SpectrumAdvancedDetailsFragment : Fragment() {

    private lateinit var detailsViewModel: SpectrumDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_advanced_details, container, false)
        detailsViewModel =
            ViewModelProviders.of(activity!!).get(SpectrumDetailsViewModel::class.java)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val ivCover = view.findViewById<ImageView>(R.id.iv_cover)
        val tvTitle = view.findViewById<TextView>(R.id.tv_title)
        val tvDeveloper = view.findViewById<TextView>(R.id.tv_developer)
        val tvDate = view.findViewById<TextView>(R.id.tv_date)
        val tvDescription = view.findViewById<TextView>(R.id.tv_description)
        val bar = view.findViewById<BarRatingView>(R.id.bar_rating)
        val web = view.findViewById<WebCriteriaView>(R.id.web_criteria)

        detailsViewModel.game.observe(this, Observer<Game> { game ->
            context?.let {
                Glide.with(it).load(
                    String.format(
                        it.getString(R.string.cover_big_placeholder),
                        game.cover.url
                    )
                )
                    .apply(RequestOptions.fitCenterTransform())
                    .into(ivCover)
                tvTitle.text = game.title
                tvDeveloper.text = game.getDeveloper()?.name
                tvDate.text = game.getReleaseYear()
                tvDescription.text = game.summary

                bar.setGameData(game)
                web.setGame(game)
            }
        })
    }
}