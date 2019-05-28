package com.epam.valkaryne.spectrumevo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.epam.valkaryne.spectrumevo.R
import com.epam.valkaryne.spectrumevo.rest.Game

class GamesAdapter(private val games: List<Game>) :
        RecyclerView.Adapter<GamesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_game, parent, false) as CardView
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val game = games[position]
        val itemView = holder.item

        val tvTitle = itemView.findViewById<TextView>(R.id.tv_card_title)

        tvTitle.text = game.title
    }

    override fun getItemCount() = games.size

    inner class ViewHolder(val item: CardView) : RecyclerView.ViewHolder(item)
}