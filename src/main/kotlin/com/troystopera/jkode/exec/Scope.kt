package com.troystopera.jkode.exec

import com.troystopera.jkode.exceptions.JKodeCompileException
import com.troystopera.jkode.vars.Var
import com.troystopera.jkode.vars.VarType

class Scope(val parent: Scope? = null) {

    private val vars = hashMapOf<VarType, HashMap<String, Var<*>?>>()

    fun declare(type: VarType, name: String, value: Var<*>? = null) {
        val map = vars[type] ?: {
            val map = hashMapOf<String, Var<*>?>()
            vars.put(type, map)
            map
        }.invoke()

        //TODO consider checking if already declared
        map.put(name, value)
    }

    fun assign(type: VarType, name: String, value: Var<*>?) {
        if (vars[type]?.containsKey(name) == true) {
            vars[type]?.put(name, value)
        } else throw JKodeCompileException("No such $type variable $name", null)
    }

    fun newChildScope(): Scope = Scope(this)

}