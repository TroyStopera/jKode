package com.troystopera.jkode.vars

import com.troystopera.jkode.Evaluation
import com.troystopera.jkode.exceptions.runtime.NullVarException
import com.troystopera.jkode.exec.Executor
import com.troystopera.jkode.exec.MutableOutput
import com.troystopera.jkode.exec.Scope

abstract class JVar<T : Any>(
        val varType: VarType<JVar<T>>,
        private val tValue: T?
) {

    abstract val eval: Evaluation<JVar<T>>

    val value: T
        get() = tValue ?: throw NullVarException()
    val isNull = tValue == null

    override fun toString() = value.toString()

    override fun equals(other: Any?) = (other as? JVar<*>)?.value?.equals(value) == true

    override fun hashCode() = value.hashCode()

}