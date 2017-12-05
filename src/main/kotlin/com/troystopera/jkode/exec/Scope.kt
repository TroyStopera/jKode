package com.troystopera.jkode.exec

import com.troystopera.jkode.JFunction
import com.troystopera.jkode.exceptions.compile.ConflictingDeclarationException
import com.troystopera.jkode.exceptions.compile.TypeCastException
import com.troystopera.jkode.exceptions.compile.UnknownTokenException
import com.troystopera.jkode.vars.JVar
import com.troystopera.jkode.vars.VarType

class Scope(val parent: Scope? = null, private val functions: HashMap<String, JFunction<*>> = hashMapOf()) {

    private val values = hashMapOf<VarType<*>, HashMap<String, JVar<*>?>>()
    private val names = hashMapOf<String, VarType<*>>()

    fun <T : JVar<*>> declare(type: VarType<T>, name: String, value: T?) {
        if (names.containsKey(name))
            throw ConflictingDeclarationException(name)
        else if (value != null && type != value.varType)
            throw TypeCastException(name, type, value.varType)

        val map = values[type] ?: {
            val map = hashMapOf<String, JVar<*>?>()
            values.put(type, map)
            map
        }.invoke()

        names.put(name, type)
        map.put(name, value)
    }

    fun assign(name: String, value: JVar<*>?) {
        if (names.containsKey(name)) {
            values[value?.varType]?.put(name, value) ?: throw TypeCastException(name, names[name], value?.varType)
        } else {
            parent?.assign(name, value) ?: throw UnknownTokenException(name)
        }
    }

    operator fun get(name: String): JVar<*> = values[names[name]]?.get(name)
            ?: parent?.get(name)
            ?: throw UnknownTokenException(name)

    fun newChildScope(): Scope = Scope(this, functions)

}