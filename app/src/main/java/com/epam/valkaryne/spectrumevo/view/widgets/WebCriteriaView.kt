package com.epam.valkaryne.spectrumevo.view.widgets

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import com.epam.valkaryne.spectrumevo.R
import com.epam.valkaryne.spectrumevo.repository.datamodel.Game
import com.epam.valkaryne.spectrumevo.repository.datamodel.spectrumfeatures.ActionCriterion
import com.epam.valkaryne.spectrumevo.repository.datamodel.spectrumfeatures.ControlCriterion
import com.epam.valkaryne.spectrumevo.repository.datamodel.spectrumfeatures.InformationCriterion
import com.github.mikephil.charting.charts.RadarChart
import com.github.mikephil.charting.data.RadarData
import com.github.mikephil.charting.data.RadarDataSet
import com.github.mikephil.charting.data.RadarEntry
import com.github.mikephil.charting.formatter.ValueFormatter

class WebCriteriaView(context: Context?, attrs: AttributeSet?) :
    RadarChart(context, attrs) {

    private var informationCriterion: InformationCriterion = InformationCriterion(0, 0, 0, 0, 0)
    private var actionCriterion: ActionCriterion = ActionCriterion(0, 0, 0, 0, 0)
    private var controlCriterion: ControlCriterion = ControlCriterion(0, 0, 0, 0, 0)

    private val criteria = arrayListOf(
        context?.getString(R.string.criterion_education),
        context?.getString(R.string.criterion_riddle),
        context?.getString(R.string.criterion_communication),
        context?.getString(R.string.criterion_role),
        context?.getString(R.string.criterion_exploration),

        context?.getString(R.string.criterion_collecting),
        context?.getString(R.string.criterion_avoidance),
        context?.getString(R.string.criterion_destruction),
        context?.getString(R.string.criterion_competition),
        context?.getString(R.string.criterion_simulation),

        context?.getString(R.string.criterion_care),
        context?.getString(R.string.criterion_building),
        context?.getString(R.string.criterion_microcontrol),
        context?.getString(R.string.criterion_tactic),
        context?.getString(R.string.criterion_plan)
    )

    init {
        description.isEnabled = false
        setBackgroundColor(ALMOST_WHITE_COLOR)

        webLineWidth = 1F
        webColor = Color.DKGRAY
        webLineWidthInner = 1F
        webColorInner = Color.DKGRAY
        webAlpha = 100

        xAxis.textSize = STANDARD_TEXT_SIZE
        xAxis.yOffset = 0F
        xAxis.xOffset = 0F
        xAxis.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return criteria[value.toInt() % criteria.size]!!
            }
        }
        xAxis.textColor = Color.BLACK

        yAxis.setLabelCount(LABEL_COUNT, false)
        yAxis.textSize = STANDARD_TEXT_SIZE
        yAxis.axisMinimum = AXIS_MINIMUM
        yAxis.axisMaximum = AXIS_MAXIMUM
        yAxis.setDrawLabels(false)

        legend.isEnabled = false

        replot()
    }

    fun setGame(game: Game) {
        informationCriterion = game.informationCriterion
        actionCriterion = game.actionCriterion
        controlCriterion = game.controlCriterion
        replot()
    }

    private fun replot() {
        val entries = ArrayList<RadarEntry>()

        entries.add(RadarEntry(informationCriterion.education.toFloat()))
        entries.add(RadarEntry(informationCriterion.riddle.toFloat()))
        entries.add(RadarEntry(informationCriterion.communication.toFloat()))
        entries.add(RadarEntry(informationCriterion.role.toFloat()))
        entries.add(RadarEntry(informationCriterion.exploration.toFloat()))

        entries.add(RadarEntry(actionCriterion.collecting.toFloat()))
        entries.add(RadarEntry(actionCriterion.avoidance.toFloat()))
        entries.add(RadarEntry(actionCriterion.destruction.toFloat()))
        entries.add(RadarEntry(actionCriterion.competition.toFloat()))
        entries.add(RadarEntry(actionCriterion.simulation.toFloat()))

        entries.add(RadarEntry(controlCriterion.care.toFloat()))
        entries.add(RadarEntry(controlCriterion.building.toFloat()))
        entries.add(RadarEntry(controlCriterion.microcontrol.toFloat()))
        entries.add(RadarEntry(controlCriterion.tactic.toFloat()))
        entries.add(RadarEntry(controlCriterion.plan.toFloat()))

        val dataSet = RadarDataSet(entries, "")
        dataSet.color = BRIGHT_GREY_COLOR
        dataSet.fillColor = BRIGHT_GREY_COLOR
        dataSet.setDrawFilled(true)
        dataSet.fillAlpha = 180
        dataSet.lineWidth = 2f
        dataSet.isDrawHighlightCircleEnabled = true
        dataSet.setDrawHighlightIndicators(false)

        val data = RadarData(dataSet)
        data.setValueTextSize(STANDARD_TEXT_SIZE)
        data.setDrawValues(false)
        data.setValueTextColor(Color.BLACK)

        this.data = data
        invalidate()
    }

    private companion object {
        const val STANDARD_TEXT_SIZE = 9F
        const val LABEL_COUNT = 15
        const val AXIS_MINIMUM = -1F
        const val AXIS_MAXIMUM = 10F
        val ALMOST_WHITE_COLOR = Color.rgb(250, 250, 250)
        val BRIGHT_GREY_COLOR = Color.rgb(103, 110, 129)
    }
}