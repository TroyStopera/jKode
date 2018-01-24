package com.troystopera.jkode.vars

import com.troystopera.jkode.Evaluation
import com.troystopera.jkode.exceptions.runtime.NullVarException

abstract class JVar<T : Any>(
        private val tValue: T?
) {

    abstract fun getVarType(): VarType<JVar<T>>

    abstract fun asEval(): Evaluation<JVar<T>>

    val value: T
        get() = tValue ?: throw NullVarException()
    val isNull = tValue == null

    override fun toString() = value.toString()

    override fun equals(other: Any?) = (other as? JVar<*>)?.value?.equals(value) == true

    override fun hashCode() = value.hashCode()

}