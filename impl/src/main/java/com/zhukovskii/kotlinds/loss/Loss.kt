package com.zhukovskii.kotlinds.loss

import com.zhukovskii.kotlinds.util.Matrix
import com.zhukovskii.kotlinds.util.Vector

interface Loss {

    val grad: LossGradient

    fun applyLoss(x: Matrix, y: Vector, w: Vector): Double
}