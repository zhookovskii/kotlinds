package com.zhukovskii.kotlinds.penalty

import com.zhukovskii.kotlinds.util.Vector
import org.jetbrains.kotlinx.multik.api.linalg.dot
import org.jetbrains.kotlinx.multik.ndarray.operations.times

class L2Penalty(val alpha: Double): BasePenalty() {

    override fun applyPenalty(w: Vector): Double = w.transpose().dot(w) * alpha

    override fun applyGradient(w: Vector): Vector = w * alpha * 2.0
}