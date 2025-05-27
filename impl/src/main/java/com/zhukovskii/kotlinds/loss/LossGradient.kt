package com.zhukovskii.kotlinds.loss

import com.zhukovskii.kotlinds.util.Matrix
import com.zhukovskii.kotlinds.util.Vector

interface LossGradient {

    fun applyGradient(x: Matrix, y: Vector, w: Vector): Vector
}