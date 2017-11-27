package com.troystopera.jkode.vars

import com.troystopera.jkode.exceptions.runtime.ArrayIndexException

class ArrayVar<out T : Any, V : Var<T>>(
        val arrayVarType: VarType,
        array: Array<V?>
) : Var<Array<V?>>(VarType.ARRAY, array) {

    operator fun get(index: Int): Var<*> = when {
        index >= value.size -> throw ArrayIndexException(value.size, index)
        else -> value[index] ?: NullVar
    }

    fun set(index: Int, value: V?) = when {
        index >= this.value.size -> throw ArrayIndexException(this.value.size, index)
        else -> this.value[index] = value
    }

}