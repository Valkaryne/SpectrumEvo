package com.epam.valkaryne.spectrumevo.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.epam.valkaryne.spectrumevo.R
import com.epam.valkaryne.spectrumevo.adapter.RatingPagesAdapter
import com.epam.valkaryne.spectrumevo.repository.datamodel.Game
import com.epam.valkaryne.spectrumevo.viewmodel.SpectrumDetailsViewModel
import it.xabaras.android.viewpagerindicator.widget.ViewPagerIndicator
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SpectrumDetailsFragment : Fragment() {

    private val detailsViewModel: SpectrumDetailsViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val ivCover = view.findViewById<ImageView>(R.id.iv_cover)
        val tvTitle = view.findViewById<TextView>(R.id.tv_title)
        val tvDeveloper = view.findViewById<TextView>(R.id.tv_developer)
        val tvDate = view.findViewById<TextView>(R.id.tv_date)
        val tvRating = view.findViewById<TextView>(R.id.tv_rating)
        val tvRatingCount = view.findViewById<TextView>(R.id.tv_rating_count)

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
                tvRating.text = String.format(
                    it.getString(R.string.relative_rating_placeholder),
                    game.getTenScaledRating()
                )
                tvRatingCount.text =
                    String.format(it.getString(R.string.rating_count_placeholder), game.ratingCount)
            }
        })

        val viewPager = view.findViewById<ViewPager>(R.id.rate_view_pager)
        val adapter = RatingPagesAdapter()
        viewPager.adapter = adapter
        viewPager.offscreenPageLimit = RATING_PAGES_COUNT

        val indicator = view.findViewById<ViewPagerIndicator>(R.id.pager_indicator)
        indicator.initWithViewPager(viewPager)

        val btnConfirm = view.findViewById<Button>(R.id.btn_confirm)
        btnConfirm.setOnClickListener {
            detailsViewModel.game.value?.let { game ->
                game.specRating = adapter.specRating
                game.informationCriterion = adapter.infoCriterion
                game.actionCriterion = adapter.actionCriterion
                game.controlCriterion = adapter.controlCriterion
                detailsViewModel.insert()
            }
        }
    }

    private companion object {
        const val RATING_PAGES_COUNT = 4
    }
}