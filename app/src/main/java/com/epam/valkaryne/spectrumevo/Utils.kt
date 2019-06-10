package com.epam.valkaryne.spectrumevo

import java.util.*
import kotlin.random.Random

fun getRandomRating(): Int {
    val rand = Random(Calendar.getInstance().timeInMillis).nextInt() % 11
    return if (rand > 0) rand else (-rand)
}