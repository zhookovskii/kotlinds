package com.zhukovskii.kotlinds.loss

import com.zhukovskii.kotlinds.util.Matrix
import com.zhukovskii.kotlinds.util.Vector
import org.jetbrains.kotlinx.multik.api.linalg.dot
import org.jetbrains.kotlinx.multik.ndarray.operations.minus
import org.jetbrains.kotlinx.multik.ndarray.operations.times

class MSELoss : BaseLoss() {

    override fun applyLoss(x: Matrix, y: Vector, w: Vector): Double {
        val diff = x.dot(w) - y
        return diff.transpose().dot(diff)
    }

    override fun applyGradient(x: Matrix, y: Vector, w: Vector): Vector =
        x.transpose().dot(
            x.dot(w) - y
        ) * 2.0
}