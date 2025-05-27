package com.zhukovskii.kotlinds.score

import com.zhukovskii.kotlinds.util.Vector
import org.jetbrains.kotlinx.multik.api.d1array
import org.jetbrains.kotlinx.multik.api.mk
import org.jetbrains.kotlinx.multik.ndarray.data.get
import org.jetbrains.kotlinx.multik.ndarray.operations.average
import kotlin.math.pow

fun accuracy(yTrue: Vector, yPred: Vector): Double {
    return mk.d1array(yTrue.size) { index ->
        if (yTrue[index] == yPred[index]) 1 else 0
    }.average()
}

fun mse(yTrue: Vector, yPred: Vector): Double {
    return mk.d1array(yTrue.size) { index ->
        (yPred[index] - yTrue[index]).pow(2)
    }.average()
}