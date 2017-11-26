package com.troystopera.jkode.exceptions

import com.troystopera.jkode.exec.CallStack

abstract class JKodeException(
        override val message: String
) : Exception() {

    private var stackTrace: CallStack? = null

    fun getStackTrace() = stackTrace

    internal fun setStackTrace(stackTrace: CallStack?) {
        this.stackTrace = stackTrace
    }

}