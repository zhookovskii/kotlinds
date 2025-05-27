package com.zhukovskii.kotlinds.exception

class NotFittedException : RuntimeException() {
    override val message: String
        get() = "Model is not fitted"
}