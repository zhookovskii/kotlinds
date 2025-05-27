package com.zhukovskii.kotlinds.penalty

import com.zhukovskii.kotlinds.util.Vector

interface Penalty {

    val grad: PenaltyGradient

    fun applyPenalty(w: Vector): Double
}