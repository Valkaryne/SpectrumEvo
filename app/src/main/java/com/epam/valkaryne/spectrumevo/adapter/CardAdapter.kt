package com.epam.valkaryne.spectrumevo.adapter

import androidx.cardview.widget.CardView

/**
 *  Adapter is needed for displaying games data on Spectrum pages in ViewPager
 *
 *  @author Valentine Litvin
 */
interface CardAdapter {

    fun getBaseElevation(): Float

    fun getCardViewAt(position: Int): CardView?

    fun getCount(): Int

    companion object {
        const val MAX_ELEVATION_FACTOR = 8
    }
}