package com.epam.valkaryne.spectrumevo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.epam.valkaryne.spectrumevo.R
import com.epam.valkaryne.spectrumevo.rest.Game

class GamesAdapter : RecyclerView.Adapter<GamesAdapter.ViewHolder>() {

    private val games: MutableList<Game> = ArrayList()
    private lateinit var context: Context

    fun addGames(games: List<Game>) {
        this.games.addAll(games)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val itemView = LayoutInflater.from(context)
            .inflate(R.layout.card_game, parent, false) as CardView
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val game = games[position]
        val itemView = holder.item

        val tvTitle = itemView.findViewById<TextView>(R.id.tv_card_title)
        val ivCover = itemView.findViewById<ImageView>(R.id.iv_card_cover)
        val tvRating = itemView.findViewById<TextView>(R.id.tv_card_rating)
        val tvGenres = itemView.findViewById<TextView>(R.id.tv_card_genres)

        Glide.with(itemView)
            .load("https:${game.cover.url}").apply(RequestOptions.fitCenterTransform())
            .error(R.drawable.game_off)
            .into(ivCover)

        tvTitle.text = game.title
        tvRating.text = String.format(context.getString(R.string.rating_placeholder), game.rating)
        tvGenres.text = game.genres.toString()
    }

    override fun getItemCount() = games.size

    inner class ViewHolder(val item: CardView) : RecyclerView.ViewHolder(item)
}