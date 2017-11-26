package com.troystopera.jkode.exceptions

abstract class JKodeRuntimeException(
        val type: Type,
        message: String
) : JKodeException(message) {

    enum class Type {
        NULL_VAR
    }

}