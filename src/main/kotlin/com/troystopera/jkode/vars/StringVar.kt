package com.troystopera.jkode.vars

class StringVar private constructor(
        value: String?
) : JVar<String>(VarType.STRING, value) {

    companion object {
        val NULL = StringVar(null)
        //TODO implement string var cache
        operator fun get(string: String) = StringVar(string)
    }

}