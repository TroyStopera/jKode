package com.troystopera.jkode.util

internal actual object Caster {

    internal actual fun <T : Any> safeCast(instance: T, any: Any): T? {
        return try {
            any.unsafeCast<T>()
        } catch (e: Exception) {
            null
        }
    }

}