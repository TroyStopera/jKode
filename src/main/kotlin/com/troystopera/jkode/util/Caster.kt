package com.troystopera.jkode.util

expect internal object Caster {
    internal fun <T : Any> safeCast(instance: T, any: Any): T?
}