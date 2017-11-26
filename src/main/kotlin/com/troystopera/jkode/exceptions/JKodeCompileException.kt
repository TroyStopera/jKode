package com.troystopera.jkode.exceptions

abstract class JKodeCompileException(
        val type: Type,
        message: String
) : JKodeException(message) {

    enum class Type {
        FUNCTION_RETURN, UNKNOWN_TOKEN, TYPE_CAST, CONFLICTING_DECLARAION
    }

}