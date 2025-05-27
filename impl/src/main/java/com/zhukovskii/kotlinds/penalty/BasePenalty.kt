package com.zhukovskii.kotlinds.penalty

abstract class BasePenalty : Penalty, PenaltyGradient {

    override val grad: PenaltyGradient
        get() = this
}