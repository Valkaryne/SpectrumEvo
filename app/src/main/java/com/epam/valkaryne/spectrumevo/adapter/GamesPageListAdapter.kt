package com.epam.valkaryne.spectrumevo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.epam.valkaryne.spectrumevo.R
import com.epam.valkaryne.spectrumevo.listeners.ItemClickListener
import com.epam.valkaryne.spectrumevo.repository.datamodel.Game

/**
 * [GamesPageListAdapter] class is responsible for deployment game items into RecyclerView.
 *
 * @author Valentine Litvin
 */

class GamesPageListAdapter(private val clickListener: ItemClickListener) :
    PagedListAdapter<Game, GamesPageListAdapter.ViewHolder>(itemComparator) {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val itemView = LayoutInflater.from(context)
            .inflate(R.layout.card_game, parent, false) as CardView
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val game = getItem(position)
        val itemView = holder.item

        val tvTitle = itemView.findViewById<TextView>(R.id.tv_card_title)
        val ivCover = itemView.findViewById<ImageView>(R.id.iv_card_cover)
        val tvRating = itemView.findViewById<TextView>(R.id.tv_card_rating)
        val tvGenres = itemView.findViewById<TextView>(R.id.tv_card_genres)

        Glide.with(itemView)
            .load(
                String.format(
                    context.getString(R.string.cover_thumb_placeholder),
                    game!!.cover.url
                )
            ).apply(
                RequestOptions.fitCenterTransform()
            )
            .error(R.drawable.ic_game_off)
            .into(ivCover)

        val genres = game.genres.toString()

        tvTitle.text = game.title
        tvRating.text =
            String.format(context.getString(R.string.rating_placeholder), game.getTenScaledRating())
        tvGenres.text = genres.substring(1, genres.length - 1)

        itemView.setOnClickListener { clickListener.onItemClick(game) }
    }

    inner class ViewHolder(val item: CardView) : RecyclerView.ViewHolder(item)

    private companion object {
        private val itemComparator = object : DiffUtil.ItemCallback<Game>() {
            override fun areItemsTheSame(oldItem: Game, newItem: Game): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Game, newItem: Game): Boolean {
                return oldItem == newItem
            }
        }
    }
}