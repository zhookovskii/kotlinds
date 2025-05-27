package com.zhukovskii.kotlinds.penalty

import com.zhukovskii.kotlinds.util.Vector
import com.zhukovskii.kotlinds.util.norm
import com.zhukovskii.kotlinds.util.sign
import org.jetbrains.kotlinx.multik.ndarray.operations.times

class L1Penalty(val alpha: Double) : BasePenalty() {

    override fun applyPenalty(w: Vector): Double = w.norm() * alpha

    override fun applyGradient(w: Vector): Vector = w.sign() * alpha
}