package com.zhukovskii.kotlinds.estimator.base

import com.zhukovskii.kotlinds.exception.SizeMismatchException
import com.zhukovskii.kotlinds.util.Matrix
import com.zhukovskii.kotlinds.util.Vector

/**
 * Base class for all estimators
 */
abstract class BaseEstimator : Estimator {

    /**
     * Whether `fit` was already called on this estimator
     */
    protected var isFitted = false

    /**
     * Number of objects in the training data
     */
    protected var n: Int = 0

    /**
     * Number of features in the training data
     */
    protected var m: Int = 0

    override fun fit(x: Matrix, y: Vector) {
        n = x.shape[0]
        m = x.shape[1]

        if (n != y.size) {
            throw SizeMismatchException("Mismatched sizes of data and target: $n and ${y.size}")
        }

        isFitted = true
    }
}