package com.zhukovskii.kotlinds.estimator.base

import com.zhukovskii.kotlinds.exception.NotFittedException
import com.zhukovskii.kotlinds.util.Matrix
import com.zhukovskii.kotlinds.util.Vector
import com.zhukovskii.kotlinds.util.addBias
import org.jetbrains.kotlinx.multik.api.linalg.dot
import org.jetbrains.kotlinx.multik.api.mk
import org.jetbrains.kotlinx.multik.api.zeros

/**
 * Base class for all linear estimators
 */
abstract class LinearEstimator : BaseEstimator() {

    /**
     * Parameters learned after fitting
     *
     * @throws NotFittedException if accessed before fitting
     */
    var weights: Vector = mk.zeros(0)
        protected set
        get() {
            if (isFitted) {
                return field
            } else {
                throw NotFittedException()
            }
        }

    override fun fit(x: Matrix, y: Vector) {
        super.fit(x, y)

        weights = mk.zeros(m + 1)
    }

    override fun predict(x: Matrix): Vector {
        return x.addBias().dot(weights)
    }
}