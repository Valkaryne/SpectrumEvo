package com.epam.valkaryne.spectrumevo.view.widgets

import android.content.Context
import android.graphics.Color
import android.text.SpannableString
import android.text.style.AbsoluteSizeSpan
import android.util.AttributeSet
import com.epam.valkaryne.spectrumevo.R
import com.epam.valkaryne.spectrumevo.repository.datamodel.Game
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate

class PieRatingLabel(context: Context?, attrs: AttributeSet?) :
    PieChart(context, attrs) {

    var textSize: Int = STANDARD_TEXT_SIZE

    var rating: Float = 0F
        set(value) {
            field = value
            generateCenterSpannableText()
        }

    private var infoValue: Float = 0F

    private var actionValue: Float = 0F

    private var controlValue: Float = 0F

    init {
        val a = context?.obtainStyledAttributes(attrs, R.styleable.PieRatingLabel)
        a?.let { attr ->
            textSize = attr.getInt(R.styleable.PieRatingLabel_textSize, STANDARD_TEXT_SIZE)
            rating = attr.getFloat(R.styleable.PieRatingLabel_rating, 0F)
            infoValue = attr.getFloat(R.styleable.PieRatingLabel_infoValue, 0F)
            actionValue = attr.getFloat(R.styleable.PieRatingLabel_actionValue, 0F)
            controlValue = attr.getFloat(R.styleable.PieRatingLabel_controlValue, 0F)
        }

        setUsePercentValues(false)
        setHoleColor(Color.parseColor("#7F000000"))
        setCenterTextColor(Color.WHITE)

        description.isEnabled = false
        setExtraOffsets(OFFSET, DOUBLED_OFFSET, OFFSET, OFFSET)

        generateCenterSpannableText()
        legend.isEnabled = false

        replot()
        a?.recycle()
    }

    fun setGameData(game: Game) {
        infoValue = game.informationCriterion.criterionValue
        actionValue = game.actionCriterion.criterionValue
        controlValue = game.controlCriterion.criterionValue
        replot()
    }

    private fun generateCenterSpannableText(rating: Float = this.rating) {
        val s = SpannableString(
            if (rating < RATING_MAXIMUM) String.format(
                context.getString(R.string.rating_placeholder),
                rating
            ) else "10"
        )
        s.setSpan(AbsoluteSizeSpan(textSize, true), 0, s.length, 0)
        centerText = s
    }

    private fun replot() {
        val entries = ArrayList<PieEntry>()

        entries.add(PieEntry(infoValue, ""))
        entries.add(PieEntry(actionValue, ""))
        entries.add(PieEntry(controlValue, ""))

        val dataSet = PieDataSet(entries, "Common Rating")
        dataSet.setDrawIcons(false)
        dataSet.sliceSpace = 1F

        val colors = ArrayList<Int>()
        colors.add(ColorTemplate.rgb("#43A047"))
        colors.add(ColorTemplate.rgb("#e53935"))
        colors.add(ColorTemplate.rgb("#1E88E5"))

        dataSet.colors = colors

        val data = PieData(dataSet)
        data.setValueTextColor(Color.TRANSPARENT)

        this.data = data
        highlightValues(null)
        invalidate()
    }

    private companion object {
        const val RATING_MAXIMUM = 10
        const val STANDARD_TEXT_SIZE = 12

        const val OFFSET = 5F
        const val DOUBLED_OFFSET = 10F
    }
}