package com.zhukovskii.kotlinds.util

import org.jetbrains.kotlinx.multik.ndarray.data.D1
import org.jetbrains.kotlinx.multik.ndarray.data.D2
import org.jetbrains.kotlinx.multik.ndarray.data.MultiArray
import org.jetbrains.kotlinx.multik.ndarray.data.MutableMultiArray

typealias Matrix = MultiArray<Double, D2>
typealias Vector = MultiArray<Double, D1>

typealias MutableVector = MutableMultiArray<Double, D1>