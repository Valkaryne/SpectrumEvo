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

    var textSize: Int = 12

    var rating: Float = 0F
        set(value) {
            field = value
            generateCenterSpannableText()
        }

    var infoValue: Float = 0F

    var actionValue: Float = 0F

    var controlValue: Float = 0F

    init {
        val a = context?.obtainStyledAttributes(attrs, R.styleable.PieRatingLabel)
        a?.let { attr ->
            textSize = attr.getInt(R.styleable.PieRatingLabel_textSize, 12)
            rating = attr.getFloat(R.styleable.PieRatingLabel_rating, 0F)
            infoValue = attr.getFloat(R.styleable.PieRatingLabel_infoValue, 0F)
            actionValue = attr.getFloat(R.styleable.PieRatingLabel_actionValue, 0F)
            controlValue = attr.getFloat(R.styleable.PieRatingLabel_controlValue, 0F)
        }

        setUsePercentValues(false)
        setHoleColor(Color.parseColor("#7F000000"))
        setCenterTextColor(Color.WHITE)

        description.isEnabled = false
        setExtraOffsets(5F, 10F, 5F, 5F)

        generateCenterSpannableText()
        legend.isEnabled = false

        setData()
        a?.recycle()
    }

    fun setGameData(game: Game) {
        infoValue = game.informationCriterion.criterionValue
        actionValue = game.actionCriterion.criterionValue
        controlValue = game.controlCriterion.criterionValue
        setData()
    }

    private fun generateCenterSpannableText(rating: Float = this.rating) {
        val s = SpannableString(if (rating < 10) rating.toString() else "10")
        s.setSpan(AbsoluteSizeSpan(textSize, true), 0, s.length, 0)
        centerText = s
    }

    private fun setData(info: Float = infoValue, action: Float = actionValue, control: Float = controlValue) {
        val entries = ArrayList<PieEntry>()

        entries.add(PieEntry(info, ""))
        entries.add(PieEntry(action, ""))
        entries.add(PieEntry(control, ""))

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
}

