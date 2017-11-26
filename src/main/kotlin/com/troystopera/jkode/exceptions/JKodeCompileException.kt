package com.troystopera.jkode.exceptions

import com.troystopera.jkode.exec.CallStack

abstract class JKodeCompileException(
        val type: Type,
        message: String,
        stackTrace: CallStack?
) : JKodeException(message, stackTrace) {

    enum class Type {
        FUNCTION_RETURN, UNKNOWN_TOKEN, TYPE_CAST, ALREADY_DECLARED
    }

}