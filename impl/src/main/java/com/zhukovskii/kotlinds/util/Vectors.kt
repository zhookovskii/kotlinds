package com.zhukovskii.kotlinds.util

import org.jetbrains.kotlinx.multik.api.linalg.dot
import org.jetbrains.kotlinx.multik.api.mk
import org.jetbrains.kotlinx.multik.api.ndarray
import org.jetbrains.kotlinx.multik.ndarray.data.get
import kotlin.math.sqrt

fun Vector.sign(): Vector =
    mk.ndarray(
        List(this.size) { index ->
            if (this[index] > 0) 1.0 else -1.0
        }
    )

fun Vector.norm(): Double {
    val squared = this.transpose().dot(this)
    if (squared == 0.0) return squared
    return sqrt(squared)
}