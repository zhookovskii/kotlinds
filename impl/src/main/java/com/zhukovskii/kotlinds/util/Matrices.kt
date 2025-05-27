package com.zhukovskii.kotlinds.util

import org.jetbrains.kotlinx.multik.api.mk
import org.jetbrains.kotlinx.multik.api.ndarray
import org.jetbrains.kotlinx.multik.ndarray.data.get
import org.jetbrains.kotlinx.multik.ndarray.operations.toList

fun Matrix.addBias() = mk.ndarray(
    List(this.shape[0]) { index ->
        this[index].toList() + listOf(1.0)
    }
)

fun Pair<Matrix, Vector>.batched(batchSize: Int) = iterator {
    val (mat, vec) = this@batched
    val n = mat.shape[0]
    var i = 0

    while (i + batchSize < n) {
        yield(mat[i until i + batchSize] to vec[i until i + batchSize])
        i += batchSize
    }

    yield(mat[i until n] to vec[i until n])
}