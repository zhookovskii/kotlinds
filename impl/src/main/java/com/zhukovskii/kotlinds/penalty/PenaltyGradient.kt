package com.zhukovskii.kotlinds.penalty

import com.zhukovskii.kotlinds.util.Vector

interface PenaltyGradient {

    fun applyGradient(w: Vector): Vector
}