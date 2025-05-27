package com.zhukovskii.kotlinds.loss

abstract class BaseLoss : Loss, LossGradient {

    override val grad: LossGradient
        get() = this
}