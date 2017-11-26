package com.troystopera.jkode.exec

import com.troystopera.jkode.exceptions.compile.JKodeTokenException
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

    fun assign(type: VarType, name: String, value: Var<*>?, executor: Executor?) {
        if (vars[type]?.containsKey(name) == true) {
            vars[type]?.put(name, value)
        } else throw JKodeTokenException(name, executor?.currentCallStack())
    }

    fun newChildScope(): Scope = Scope(this)

}