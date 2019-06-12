package com.epam.valkaryne.spectrumevo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.epam.valkaryne.spectrumevo.R
import com.epam.valkaryne.spectrumevo.adapter.CardAdapter.Companion.MAX_ELEVATION_FACTOR
import com.epam.valkaryne.spectrumevo.listeners.ItemClickListener
import com.epam.valkaryne.spectrumevo.repository.datamodel.Game
import com.epam.valkaryne.spectrumevo.view.widgets.PieRatingLabel

class GamesCardAdapter(private val clickListener: ItemClickListener) : PagerAdapter(), CardAdapter {

    private val views: MutableList<CardView?> = ArrayList()
    private val data: MutableList<CardItem> = ArrayList()
    private var baseElevation = 0F

    fun submitList(items: List<Game>) {
        clearAdapter()
        items.forEach {
            views.add(null)
            data.add(CardItem(it))
            notifyDataSetChanged()
        }
    }

    override fun getBaseElevation() = baseElevation

    override fun getCardViewAt(position: Int): CardView? {
        return views[position]
    }

    override fun getCount() = views.size

    override fun isViewFromObject(view: View, `object`: Any) = view == `object`

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(container.context)
            .inflate(R.layout.card_adapter, container, false)

        container.addView(view)
        bind(container.context, data[position], view)
        val cardView = view.findViewById<CardView>(R.id.card_view)

        if (baseElevation == 0F) {
            baseElevation = cardView.cardElevation
        }

        cardView.maxCardElevation = baseElevation * MAX_ELEVATION_FACTOR
        cardView.setOnClickListener { clickListener.onItemClick(data[position].game) }
        views[position] = cardView
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
        views[position] = null
    }

    private fun bind(context: Context, item: CardItem, view: View) {
        val tvTitle = view.findViewById<TextView>(R.id.tv_item_title)
        val ivCover = view.findViewById<ImageView>(R.id.iv_item_cover)
        val pieItemRating = view.findViewById<PieRatingLabel>(R.id.pie_item_rating)

        tvTitle.text = item.title
        Glide.with(context)
            .load(
                String.format(
                    context.getString(R.string.cover_big_placeholder),
                    item.coverUrl
                )
            )
            .apply(RequestOptions.fitCenterTransform())
            .into(ivCover)
        pieItemRating.setGameData(item.game)
        pieItemRating.rating = item.rating
    }

    private fun clearAdapter() {
        views.clear()
        data.clear()
    }
}