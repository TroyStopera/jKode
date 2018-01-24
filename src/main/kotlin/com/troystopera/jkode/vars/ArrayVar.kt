package com.troystopera.jkode.vars

import com.troystopera.jkode.Evaluation
import com.troystopera.jkode.exceptions.runtime.ArrayIndexException
import com.troystopera.jkode.exec.Executor
import com.troystopera.jkode.exec.MutableOutput
import com.troystopera.jkode.exec.Scope

open class ArrayVar<T : JVar<*>>(
        val arrayVarType: VarType<T>,
        array: Array<T?>?
) : JVar<Array<T?>>(array) {

    override fun getVarType(): VarType<ArrayVar<T>> = VarType.ARRAY[arrayVarType]

    override fun asEval(): Evaluation<ArrayVar<T>> = object : Evaluation<ArrayVar<T>>(getVarType()) {
        override fun onExecute(scope: Scope, output: MutableOutput?, executor: Executor?) = this@ArrayVar
    }

    operator fun get(index: Int): T = when {
        index >= value.size -> throw ArrayIndexException(value.size, index)
        else -> value[index] ?: arrayVarType.NULL
    }

    fun set(index: Int, value: T?) = when {
        index >= this.value.size -> throw ArrayIndexException(this.value.size, index)
        else -> this.value[index] = value
    }

    companion object {
        val NULL = ArrayVar<JVar<*>>(VarType.UNIT, null)
    }

}