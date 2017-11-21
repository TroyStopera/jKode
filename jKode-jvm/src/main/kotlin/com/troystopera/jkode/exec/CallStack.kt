package com.troystopera.jkode.exec

import kotlin.reflect.KClass

actual class CallStack<E : Executable> private constructor(
        actual val base: String,
        actual val callers: List<String>
) {

    private val string = base + callers.joinToString { "@" + it }

    constructor(override: KClass<E>, vararg callers: KClass<Executable>) :
            this(classToString(override), callers.map { classToString(it) })

    actual override fun toString(): String = string

    actual override fun equals(other: Any?): Boolean =
            if (other !is CallStack<*>) false
            else string == other.string

    actual override fun hashCode(): Int = string.hashCode()

    actual companion object {

        actual fun execToString(executable: Executable): String = classToString(executable::class)

        actual fun callerToString(caller: Caller?): String = if (caller == null) ""
        else execToString(caller.executable) + callerToString(caller.parentCaller)

        private fun classToString(clazz: KClass<*>): String =
                clazz.qualifiedName ?: clazz.simpleName ?: clazz.java.name + "@"

    }

}