package com.zhukovskii.kotlinds.estimator.linear

import com.zhukovskii.kotlinds.estimator.base.LinearEstimator
import com.zhukovskii.kotlinds.penalty.L2Penalty
import com.zhukovskii.kotlinds.penalty.NoPenalty
import com.zhukovskii.kotlinds.penalty.Penalty
import com.zhukovskii.kotlinds.util.Matrix
import com.zhukovskii.kotlinds.util.Vector
import com.zhukovskii.kotlinds.util.addBias
import org.jetbrains.kotlinx.multik.api.identity
import org.jetbrains.kotlinx.multik.api.linalg.dot
import org.jetbrains.kotlinx.multik.api.linalg.inv
import org.jetbrains.kotlinx.multik.ndarray.operations.*
import org.jetbrains.kotlinx.multik.api.Multik as mk

/**
 * Least squares linear regression
 *
 * @param penalty Regularization method to use (only `Penalty.None` or `Penalty.L2` are allowed for this estimator)
 */
class LinearRegression(
    private val penalty: Penalty,
) : LinearEstimator() {

    override fun fit(x: Matrix, y: Vector) {
        super.fit(x, y)

        val regularizationCo = when (penalty) {
            is NoPenalty -> 0.0
            is L2Penalty -> penalty.alpha
            else -> throw IllegalArgumentException("Only Penalty.None or Penalty.L2 are allowed for this estimator")
        }

        val xBiased: Matrix = x.addBias()
        val regularizationTerm = mk.identity<Double>(m + 1).times(regularizationCo)

        weights = mk.linalg.inv(
            xBiased.transpose()
                .dot(xBiased)
                .plus(regularizationTerm)
        ).dot(xBiased.transpose())
            .dot(y)
    }
}