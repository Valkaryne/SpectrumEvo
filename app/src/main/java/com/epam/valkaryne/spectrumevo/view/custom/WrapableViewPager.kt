package com.epam.valkaryne.spectrumevo.view.custom

import android.content.Context
import android.util.AttributeSet
import androidx.viewpager.widget.ViewPager

class WrapableViewPager(context: Context, attrs: AttributeSet?) : ViewPager(context, attrs) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var height = 0
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED))
            val h = child.measuredHeight
            if (h > height) height = h
        }

        val heightSpec = if (height != 0) MeasureSpec.makeMeasureSpec(
            height,
            MeasureSpec.EXACTLY
        ) else heightMeasureSpec
        super.onMeasure(widthMeasureSpec, heightSpec)
    }
}