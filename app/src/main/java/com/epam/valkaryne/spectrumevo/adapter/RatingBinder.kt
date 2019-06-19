package com.epam.valkaryne.spectrumevo.adapter

import android.view.View
import com.epam.valkaryne.spectrumevo.R
import com.epam.valkaryne.spectrumevo.repository.datamodel.spectrumfeatures.ActionCriterion
import com.epam.valkaryne.spectrumevo.repository.datamodel.spectrumfeatures.ControlCriterion
import com.epam.valkaryne.spectrumevo.repository.datamodel.spectrumfeatures.InformationCriterion
import com.epam.valkaryne.spectrumevo.repository.datamodel.spectrumfeatures.SpecRating
import com.iarcuschin.simpleratingbar.SimpleRatingBar

/**
 * Utility object for binding pages of nested ViewPager.
 *
 * @author Valentine Litvin
 */
object RatingBinder {
    fun bindCommonRating(view: View, specRating: SpecRating) {
        val facadeBar = view.findViewById<SimpleRatingBar>(R.id.facade_bar)
        val mechanicsBar = view.findViewById<SimpleRatingBar>(R.id.mechanics_bar)
        val contentBar = view.findViewById<SimpleRatingBar>(R.id.content_bar)

        facadeBar.setOnRatingBarChangeListener { _, rating, _ ->
            specRating.facade = rating.toInt()
        }

        mechanicsBar.setOnRatingBarChangeListener { _, rating, _ ->
            specRating.mechanics = rating.toInt()
        }

        contentBar.setOnRatingBarChangeListener { _, rating, _ ->
            specRating.content = rating.toInt()
        }
    }

    fun bindInfoCriteria(view: View, infoCriterion: InformationCriterion) {
        val educationBar = view.findViewById<SimpleRatingBar>(R.id.education_bar)
        val riddleBar = view.findViewById<SimpleRatingBar>(R.id.riddle_bar)
        val communicationBar = view.findViewById<SimpleRatingBar>(R.id.communication_bar)
        val roleBar = view.findViewById<SimpleRatingBar>(R.id.role_bar)
        val explorationBar = view.findViewById<SimpleRatingBar>(R.id.exploration_bar)

        educationBar.setOnRatingBarChangeListener { _, rating, _ ->
            infoCriterion.education = rating.toInt()
        }

        riddleBar.setOnRatingBarChangeListener { _, rating, _ ->
            infoCriterion.riddle = rating.toInt()
        }

        communicationBar.setOnRatingBarChangeListener { _, rating, _ ->
            infoCriterion.communication = rating.toInt()
        }

        roleBar.setOnRatingBarChangeListener { _, rating, _ ->
            infoCriterion.role = rating.toInt()
        }

        explorationBar.setOnRatingBarChangeListener { _, rating, _ ->
            infoCriterion.exploration = rating.toInt()
        }
    }

    fun bindActionCriteria(view: View, actionCriterion: ActionCriterion) {
        val collectingBar = view.findViewById<SimpleRatingBar>(R.id.collecting_bar)
        val avoidanceBar = view.findViewById<SimpleRatingBar>(R.id.avoidance_bar)
        val destructionBar = view.findViewById<SimpleRatingBar>(R.id.destruction_bar)
        val competitionBar = view.findViewById<SimpleRatingBar>(R.id.competition_bar)
        val simulationBar = view.findViewById<SimpleRatingBar>(R.id.simulation_bar)

        collectingBar.setOnRatingBarChangeListener { _, rating, _ ->
            actionCriterion.collecting = rating.toInt()
        }

        avoidanceBar.setOnRatingBarChangeListener { _, rating, _ ->
            actionCriterion.avoidance = rating.toInt()
        }

        destructionBar.setOnRatingBarChangeListener { _, rating, _ ->
            actionCriterion.destruction = rating.toInt()
        }

        competitionBar.setOnRatingBarChangeListener { _, rating, _ ->
            actionCriterion.competition = rating.toInt()
        }

        simulationBar.setOnRatingBarChangeListener { _, rating, _ ->
            actionCriterion.simulation = rating.toInt()
        }
    }

    fun bindControlCriteria(view: View, controlCriterion: ControlCriterion) {
        val careBar = view.findViewById<SimpleRatingBar>(R.id.care_bar)
        val buildingBar = view.findViewById<SimpleRatingBar>(R.id.building_bar)
        val microcontrolBar = view.findViewById<SimpleRatingBar>(R.id.microcontrol_bar)
        val tacticBar = view.findViewById<SimpleRatingBar>(R.id.tactic_bar)
        val planBar = view.findViewById<SimpleRatingBar>(R.id.plan_bar)

        careBar.setOnRatingBarChangeListener { _, rating, _ ->
            controlCriterion.care = rating.toInt()
        }

        buildingBar.setOnRatingBarChangeListener { _, rating, _ ->
            controlCriterion.building = rating.toInt()
        }

        microcontrolBar.setOnRatingBarChangeListener { _, rating, _ ->
            controlCriterion.microcontrol = rating.toInt()
        }

        tacticBar.setOnRatingBarChangeListener { _, rating, _ ->
            controlCriterion.tactic = rating.toInt()
        }

        planBar.setOnRatingBarChangeListener { _, rating, _ ->
            controlCriterion.plan = rating.toInt()
        }
    }
}