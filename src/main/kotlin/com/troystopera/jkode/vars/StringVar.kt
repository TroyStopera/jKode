package com.troystopera.jkode.vars

class StringVar private constructor(
        value: String?
) : Var<String>(STRING, value) {

    companion object {
        val NULL = StringVar(null)
        //TODO implement string var cache
        operator fun get(string: String) = StringVar(string)
    }

}