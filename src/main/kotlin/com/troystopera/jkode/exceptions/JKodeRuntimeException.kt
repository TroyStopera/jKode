package com.troystopera.jkode.exceptions

import com.troystopera.jkode.exec.CallStack

abstract class JKodeRuntimeException(
        val type: Type,
        message: String,
        stackTrace: CallStack?
) : JKodeException(message, stackTrace) {

    enum class Type {
        NULL_POINTER
    }

}