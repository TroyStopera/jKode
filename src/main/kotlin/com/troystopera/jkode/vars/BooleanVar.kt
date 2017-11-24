package com.troystopera.jkode.vars

class BooleanVar private constructor(value: Boolean) : Var<Boolean>(VarType.BOOLEAN, value) {

    companion object {
        val True = BooleanVar(true)
        val False = BooleanVar(false)
    }

}