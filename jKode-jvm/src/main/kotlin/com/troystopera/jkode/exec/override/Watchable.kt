package com.troystopera.jkode.exec.override

import com.troystopera.jkode.exec.CallStack
import com.troystopera.jkode.exec.Executable
import kotlin.reflect.KClass

actual class Watchable<E : Executable<*>> private constructor(
        private val base: String,
        private val callers: List<String>
) : CallStack() {

    //cached string to avoid O(n) toString
    private val string = super.toString()

    actual override fun getBase() = base

    actual override fun getCallers() = callers

    constructor(override: KClass<E>, vararg callers: KClass<Executable<*>>) :
            this(classToString(override), callers.map { classToString(it) })

    override fun toString(): String = string

    actual companion object {

        actual fun execToString(executable: Executable<*>): String = classToString(executable::class)

        private fun classToString(clazz: KClass<*>): String =
                clazz.qualifiedName ?: clazz.simpleName ?: clazz.java.name

    }

}