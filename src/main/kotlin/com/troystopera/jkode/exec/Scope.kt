package com.troystopera.jkode.exec

import com.troystopera.jkode.exceptions.compile.JKodeDeclareException
import com.troystopera.jkode.exceptions.compile.JKodeTokenException
import com.troystopera.jkode.exceptions.compile.JKodeTypeException
import com.troystopera.jkode.vars.Var
import com.troystopera.jkode.vars.VarType

class Scope(val parent: Scope? = null) {

    private val values = hashMapOf<VarType, HashMap<String, Var<*>?>>()
    private val names = hashMapOf<String, VarType>()

    fun declare(type: VarType, name: String, value: Var<*>?, executor: Executor?) {
        if (names.containsKey(name))
            throw JKodeDeclareException(name, executor?.currentCallStack())
        val map = values[type] ?: {
            val map = hashMapOf<String, Var<*>?>()
            values.put(type, map)
            map
        }.invoke()

        names.put(name, type)
        map.put(name, value)
    }

    fun assign(name: String, value: Var<*>?, executor: Executor?) {
        if (names.containsKey(name)) {
            values[value?.varType]?.put(name, value) ?: throw JKodeTypeException(name, names[name], value?.varType, executor?.currentCallStack())
        } else {
            parent?.assign(name, value, executor) ?: throw JKodeTokenException(name, executor?.currentCallStack())
        }
    }

    operator fun get(name: String, executor: Executor?): Var<*>? = values[names[name]]?.get(name)
            ?: parent?.get(name, executor)
            ?: throw JKodeTokenException(name, executor?.currentCallStack())

    fun newChildScope(): Scope = Scope(this)

}