package com.troystopera.jkode.vars

class BooleanVar private constructor(
        value: Boolean?
) : JVar<Boolean>(BOOLEAN, value) {

    companion object {

        val TRUE = BooleanVar(true)
        val FALSE = BooleanVar(false)
        val NULL = BooleanVar(null)

        operator fun get(b: Boolean) = if (b) TRUE else FALSE

    }

}