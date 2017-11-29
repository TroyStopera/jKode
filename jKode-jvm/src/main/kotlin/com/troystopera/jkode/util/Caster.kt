package com.troystopera.jkode.util

import kotlin.reflect.full.safeCast

internal actual object Caster {

    internal actual fun <T : Any> safeCast(instance: T, any: Any): T? = instance::class.safeCast(any)

}