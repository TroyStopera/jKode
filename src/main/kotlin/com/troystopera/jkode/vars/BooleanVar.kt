package com.troystopera.jkode.vars

class BooleanVar private constructor(value: Boolean) : Var<Boolean>(VarType.BOOLEAN, value) {

    companion object {

        val TRUE = BooleanVar(true)
        val FALSE = BooleanVar(false)

        operator fun get(b: Boolean) = if (b) TRUE else FALSE

    }

}