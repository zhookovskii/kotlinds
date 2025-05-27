package com.zhukovskii.kotlinds.penalty

import com.zhukovskii.kotlinds.util.Vector
import org.jetbrains.kotlinx.multik.api.mk
import org.jetbrains.kotlinx.multik.api.zeros

class NoPenalty : BasePenalty() {

    override fun applyPenalty(w: Vector): Double = 0.0

    override fun applyGradient(w: Vector): Vector = mk.zeros(w.size)
}