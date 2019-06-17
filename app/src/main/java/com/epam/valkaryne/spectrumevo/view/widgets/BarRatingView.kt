package com.epam.valkaryne.spectrumevo.view.widgets

import android.content.Context
import android.util.AttributeSet
import com.epam.valkaryne.spectrumevo.R
import com.epam.valkaryne.spectrumevo.repository.datamodel.Game
import com.github.mikephil.charting.charts.HorizontalBarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.utils.ColorTemplate

class BarRatingView(context: Context?, attrs: AttributeSet?) :
    HorizontalBarChart(context, attrs) {

    private var facadeRating: Float = 0F

    private var mechanicsRating: Float = 0F

    private var contentRating: Float = 0F

    private var textColor: Int = 0

    init {
        val a = context?.obtainStyledAttributes(attrs, R.styleable.BarRatingView)
        a?.let { attr ->
            facadeRating = attr.getFloat(R.styleable.BarRatingView_facadeRating, 0F)
            mechanicsRating = attr.getFloat(R.styleable.BarRatingView_mechanicsRating, 0F)
            contentRating = attr.getFloat(R.styleable.BarRatingView_contentRating, 0F)
            textColor = attr.getColor(R.styleable.BarRatingView_textColor, 0)
        }

        description.isEnabled = false
        axisLeft.setDrawLabels(false)
        axisLeft.setDrawGridLines(false)
        axisLeft.axisMaximum = AXIS_MAXIMUM
        axisLeft.axisMinimum = AXIS_MINIMUM
        axisRight.setDrawLabels(false)
        axisRight.setDrawGridLines(false)
        axisRight.axisMaximum = AXIS_MAXIMUM
        axisRight.axisMinimum = AXIS_MINIMUM
        xAxis.setDrawLabels(false)
        xAxis.setDrawGridLines(false)

        legend.textColor = textColor

        setFitBars(true)

        replot()
        a?.recycle()
    }

    fun setGameData(game: Game) {
        facadeRating = game.specRating.facade.toFloat()
        mechanicsRating = game.specRating.mechanics.toFloat()
        contentRating = game.specRating.content.toFloat()
        replot()
    }

    private fun replot() {
        val barWidth = BAR_WIDTH
        val spaceForBar = 1F

        val contentValues = ArrayList<BarEntry>()
        contentValues.add(BarEntry(0 * spaceForBar, contentRating))
        val contentSet = BarDataSet(contentValues, "Content")
        contentSet.color = ColorTemplate.rgb("#43A047")

        val mechanicsValues = ArrayList<BarEntry>()
        mechanicsValues.add(BarEntry(1 * spaceForBar, mechanicsRating))
        val mechanicsSet = BarDataSet(mechanicsValues, "Mechanics")
        mechanicsSet.color = ColorTemplate.rgb("#1E88E5")

        val facadeValues = ArrayList<BarEntry>()
        facadeValues.add(BarEntry(2 * spaceForBar, facadeRating))
        val facadeSet = BarDataSet(facadeValues, "Facade")
        facadeSet.color = ColorTemplate.rgb("#e53935")

        val dataSets = ArrayList<IBarDataSet>()
        dataSets.add(contentSet)
        dataSets.add(mechanicsSet)
        dataSets.add(facadeSet)

        val data = BarData(dataSets)
        data.setValueTextSize(STANDARD_TEXT_SIZE)
        data.setValueTextColor(textColor)
        data.barWidth = barWidth
        this.data = data
    }

    private companion object {
        const val AXIS_MINIMUM = 0F
        const val AXIS_MAXIMUM = 10F
        const val STANDARD_TEXT_SIZE = 10F
        const val BAR_WIDTH = 0.9F
    }
}