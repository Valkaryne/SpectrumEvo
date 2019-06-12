package com.epam.valkaryne.spectrumevo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.epam.valkaryne.spectrumevo.R
import com.epam.valkaryne.spectrumevo.repository.datamodel.spectrumfeatures.ActionCriterion
import com.epam.valkaryne.spectrumevo.repository.datamodel.spectrumfeatures.ControlCriterion
import com.epam.valkaryne.spectrumevo.repository.datamodel.spectrumfeatures.InformationCriterion
import com.epam.valkaryne.spectrumevo.repository.datamodel.spectrumfeatures.SpecRating

class RatingPagesAdapter : PagerAdapter() {

    val specRating = SpecRating(0, 0, 0)
    val infoCriterion = InformationCriterion(0, 0, 0, 0, 0)
    val actionCriterion = ActionCriterion(0, 0, 0, 0, 0)
    val controlCriterion = ControlCriterion(0, 0, 0, 0, 0)

    override fun isViewFromObject(view: View, `object`: Any) = view == `object`

    override fun getCount() = PAGES_COUNT

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = when (getType(position)) {
            TYPE_COMMON_RATING -> inflate(container, R.layout.layout_common_rating)
            TYPE_INFO_CRITERIA -> inflate(container, R.layout.layout_info_criteria)
            TYPE_ACTION_CRITERIA -> inflate(container, R.layout.layout_action_criteria)
            TYPE_CONTROL_CRITERIA -> inflate(container, R.layout.layout_control_criteria)
            else -> View(container.context)
        }

        container.addView(view)
        bind(view, position)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    private fun getType(position: Int): Int {
        return when (position) {
            RATING_PAGE -> TYPE_COMMON_RATING
            INFO_PAGE -> TYPE_INFO_CRITERIA
            ACTION_PAGE -> TYPE_ACTION_CRITERIA
            CONTROL_PAGE -> TYPE_CONTROL_CRITERIA
            else -> TYPE_UNKNOWN
        }
    }

    private fun bind(view: View, position: Int) {
        when (getType(position)) {
            TYPE_COMMON_RATING -> RatingBinder.bindCommonRating(view, specRating)
            TYPE_INFO_CRITERIA -> RatingBinder.bindInfoCriteria(view, infoCriterion)
            TYPE_ACTION_CRITERIA -> RatingBinder.bindActionCriteria(view, actionCriterion)
            TYPE_CONTROL_CRITERIA -> RatingBinder.bindControlCriteria(view, controlCriterion)
        }
    }

    private fun inflate(container: ViewGroup, layoutResource: Int): View =
        LayoutInflater.from(container.context).inflate(
            layoutResource,
            container,
            false
        )

    private companion object {
        const val TYPE_COMMON_RATING = 110
        const val TYPE_INFO_CRITERIA = 111
        const val TYPE_ACTION_CRITERIA = 112
        const val TYPE_CONTROL_CRITERIA = 113
        const val TYPE_UNKNOWN = -1

        const val RATING_PAGE = 0
        const val INFO_PAGE = 1
        const val ACTION_PAGE = 2
        const val CONTROL_PAGE = 3

        const val PAGES_COUNT = 4
    }
}