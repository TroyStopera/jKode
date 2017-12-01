package com.troystopera.jkode.vars

import com.troystopera.jkode.Evaluation
import com.troystopera.jkode.exceptions.runtime.NullVarException
import com.troystopera.jkode.exec.Executor
import com.troystopera.jkode.exec.MutableOutput
import com.troystopera.jkode.exec.Scope

abstract class Var<T : Any>(
        type: VarType<Var<T>>,
        private val tValue: T?
) : Evaluation<Var<T>>(type) {

    val value: T
        get() = tValue ?: throw NullVarException()
    val isNull = tValue == null

    override fun toString() = value.toString()

    override fun equals(other: Any?) = (other as? Var<*>)?.value?.equals(value) == true

    override fun hashCode() = value.hashCode()

    override fun onExecute(scope: Scope, output: MutableOutput?, executor: Executor?) = this

}