package com.troystopera.jkode.vars

import com.troystopera.jkode.exceptions.runtime.ArrayIndexException

open class ArrayVar<T : Var<*>>(
        val arrayVarType: VarType<T>,
        array: Array<T?>?
) : Var<Array<T?>>(ARRAY[arrayVarType], array) {

    operator fun get(index: Int): T = when {
        index >= value.size -> throw ArrayIndexException(value.size, index)
        else -> value[index] ?: arrayVarType.NULL
    }

    fun set(index: Int, value: T?) = when {
        index >= this.value.size -> throw ArrayIndexException(this.value.size, index)
        else -> this.value[index] = value
    }

    companion object {
        val NULL = ArrayVar<Var<*>>(ARRAY, null)
    }

}