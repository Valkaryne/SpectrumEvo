package com.epam.valkaryne.spectrumevo.adapter

import android.view.View
import androidx.viewpager.widget.ViewPager

class ViewPagerTransformer(private val viewPager: ViewPager, private val adapter: CardAdapter)
    : ViewPager.OnPageChangeListener, ViewPager.PageTransformer {

    private var lastOffset = 0F
    private var scalingEnabled = false

    init {
        viewPager.addOnPageChangeListener(this)
    }

    override fun transformPage(page: View, position: Float) {}

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        val realCurrentPosition: Int
        val nextPosition: Int
        val baseElevation = adapter.getBaseElevation()
        val realOffset: Float
        val goingLeft = lastOffset > positionOffset

        if (goingLeft) {
            realCurrentPosition = position + 1
            nextPosition = position
            realOffset = 1 - positionOffset
        } else {
            nextPosition = position + 1
            realCurrentPosition = position
            realOffset = positionOffset
        }

        if (nextPosition > adapter.getCount() - 1 || realCurrentPosition > adapter.getCount() - 1) {
            return
        }

        val currentCard = adapter.getCardViewAt(realCurrentPosition)
        if (scalingEnabled) {
            currentCard?.let {
                it.scaleX = (1 + 0.1 * (1 - realOffset)).toFloat()
                it.scaleY = (1 + 0.1 * (1 - realOffset)).toFloat()
            }
        }
        currentCard?.cardElevation = (baseElevation + baseElevation
                * (CardAdapter.MAX_ELEVATION_FACTOR - 1) * (1 - realOffset))

        val nextCard = adapter.getCardViewAt(nextPosition)

        if (scalingEnabled) {
            nextCard?.let {
                it.scaleX = (1 + 0.1 * (realOffset)).toFloat()
                it.scaleY = (1 + 0.1 * (realOffset)).toFloat()
            }
        }
        nextCard?.cardElevation = (baseElevation + baseElevation
                * (CardAdapter.MAX_ELEVATION_FACTOR - 1) * (realOffset))

        lastOffset = positionOffset
    }

    override fun onPageSelected(position: Int) {}

    override fun onPageScrollStateChanged(state: Int) {}

    fun enableScaling(enable: Boolean) {
        if (scalingEnabled && !enable) {
            val currentCard = adapter.getCardViewAt(viewPager.currentItem)
            currentCard?.animate()?.scaleY(1F)
            currentCard?.animate()?.scaleX(1F)
        } else if (!scalingEnabled && enable) {
            val currentCard = adapter.getCardViewAt(viewPager.currentItem)
            currentCard?.animate()?.scaleY(ANIMATE_SCALED_VALUE)
            currentCard?.animate()?.scaleX(ANIMATE_SCALED_VALUE)
        }

        scalingEnabled = enable
    }

    private companion object {
        const val ANIMATE_SCALED_VALUE = 1.1F
    }
}