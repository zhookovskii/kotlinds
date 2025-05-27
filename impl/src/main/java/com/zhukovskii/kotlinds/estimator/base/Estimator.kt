package com.zhukovskii.kotlinds.estimator.base

import com.zhukovskii.kotlinds.util.Matrix
import com.zhukovskii.kotlinds.util.Vector

interface Estimator {

    /**
     * Fit model with provided data and target
     *
     * @param x Data
     * @param y Target
     */
    fun fit(x: Matrix, y: Vector)

    /**
     * Predict targets for provided data
     *
     * @param x Data
     */
    fun predict(x: Matrix): Vector
}