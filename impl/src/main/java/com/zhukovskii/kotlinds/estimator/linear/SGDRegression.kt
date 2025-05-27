package com.zhukovskii.kotlinds.estimator.linear

import com.zhukovskii.kotlinds.estimator.base.LinearEstimator
import com.zhukovskii.kotlinds.loss.Loss
import com.zhukovskii.kotlinds.loss.MSELoss
import com.zhukovskii.kotlinds.penalty.NoPenalty
import com.zhukovskii.kotlinds.penalty.Penalty
import com.zhukovskii.kotlinds.util.*
import org.jetbrains.kotlinx.multik.ndarray.data.set
import org.jetbrains.kotlinx.multik.ndarray.operations.minus
import org.jetbrains.kotlinx.multik.ndarray.operations.plus
import org.jetbrains.kotlinx.multik.ndarray.operations.times
import kotlin.math.abs

/**
 * Linear model fitted by minimizing provided loss function regularized with provided penalty via Stochastic Gradient Descent (SGD)
 *
 * @param epochs Maximum number of iterations over training data
 * @param batchSize Number of objects in one batch
 * @param learningRate Learning rate coefficient
 * @param loss Empirical loss function
 * @param penalty Regularization method to use
 * @param stopWhenWeightsUnchanged Whether to stop fitting when weights remain unchanged over two epochs
 * @param weightsTolerance Tolerance to which weights are considered unchanged
 * @param stopWhenLossUnchanged Whether to stop fitting when the value of the loss function remains unchanged over two epochs
 * @param lossTolerance Tolerance to which the value of the loss function is considered unchanged
 * @param trackLoss Whether to track loss over epochs when fitting. If set to `true`, the accumulated values will be stored in `trainHistory`
 */
class SGDRegression(
    private val epochs: Int,
    private val batchSize: Int = 16,
    private val learningRate: Double = 1e-2,
    private val loss: Loss = MSELoss(),
    private val penalty: Penalty = NoPenalty(),
    private val stopWhenWeightsUnchanged: Boolean = true,
    private val weightsTolerance: Double = 1e-3,
    private val stopWhenLossUnchanged: Boolean = true,
    private val lossTolerance: Double = 1e-3,
    private val trackLoss: Boolean = false,
) : LinearEstimator() {

    private val _trainHistory: MutableList<Double> = mutableListOf()
    val trainHistory: List<Double> = _trainHistory

    private lateinit var prevWeights: Vector

    override fun fit(x: Matrix, y: Vector) {
        super.fit(x, y)

        prevWeights = weights
        val xBiased = x.addBias()

        for (i in 0 until epochs) {
            for ((batchX, batchY) in (xBiased to y).batched(batchSize = batchSize)) {
                val regularizationTerm = penalty.grad.applyGradient(weights) as MutableVector
                regularizationTerm[m] = 0.0
                val lossTerm = loss.grad.applyGradient(batchX, batchY, weights) * (1.0 / batchSize)
                val step = lossTerm + regularizationTerm

                weights -= step * learningRate

                if (stopWhenWeightsUnchanged && (weights - prevWeights).norm() < weightsTolerance)
                    break

                prevWeights = weights
            }

            val newLoss = loss.applyLoss(xBiased, y, weights)

            if (stopWhenLossUnchanged && trainHistory.isNotEmpty() && abs(newLoss - trainHistory.last()) < lossTolerance)
                break

            _trainHistory.add(newLoss)
        }
    }
}