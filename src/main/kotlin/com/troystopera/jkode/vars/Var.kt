package com.troystopera.jkode.vars

import com.troystopera.jkode.Evaluation
import com.troystopera.jkode.exec.Executor
import com.troystopera.jkode.exec.MutableOutput
import com.troystopera.jkode.exec.Scope

abstract class Var<out T : Any>(
        type: VarType,
        open val value: T
) : Evaluation<Var<T>>(type) {

    override fun toString() = value.toString()

    override fun equals(other: Any?) = (other as? Var<*>)?.value?.equals(value) ?: false

    override fun hashCode() = value.hashCode()

    override fun onExecute(scope: Scope, output: MutableOutput?, executor: Executor?) = this

}