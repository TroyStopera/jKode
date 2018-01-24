package com.troystopera.jkode.vars

import com.troystopera.jkode.exceptions.runtime.ArrayIndexException

open class ArrayVar<T : JVar<*>>(
        val arrayVarType: VarType<T>,
        array: Array<T?>?
) : JVar<Array<T?>>(array) {

    override fun getVarType(): VarType<ArrayVar<T>> = VarType.ARRAY[arrayVarType]

    override fun asEval(): JVarEvaluation<ArrayVar<T>> = JVarEvaluation(this, getVarType())

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