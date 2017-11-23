package com.troystopera.jkode.vars

abstract class Var<out T : Any>(val type: VarType, val value: T) {

    override fun toString(): String = value.toString()

    override fun equals(other: Any?): Boolean = (other as? Var<*>)?.value?.equals(value) ?: false

    override fun hashCode(): Int = value.hashCode()

}