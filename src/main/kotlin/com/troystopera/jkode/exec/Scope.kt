package com.troystopera.jkode.exec

import com.troystopera.jkode.Function
import com.troystopera.jkode.exceptions.compile.ConflictingDeclarationException
import com.troystopera.jkode.exceptions.compile.TypeCastException
import com.troystopera.jkode.exceptions.compile.UnknownTokenException
import com.troystopera.jkode.vars.NullVar
import com.troystopera.jkode.vars.Var
import com.troystopera.jkode.vars.VarType

class Scope(val parent: Scope? = null, private val functions: HashMap<String, Function<*>> = hashMapOf()) {

    private val values = hashMapOf<VarType, HashMap<String, Var<*>?>>()
    private val names = hashMapOf<String, VarType>()

    fun declare(type: VarType, name: String, value: Var<*>?) {
        if (names.containsKey(name))
            throw ConflictingDeclarationException(name)
        else if (value != null && type != value.varType)
            throw TypeCastException(name, type, value.varType)

        val map = values[type] ?: {
            val map = hashMapOf<String, Var<*>?>()
            values.put(type, map)
            map
        }.invoke()

        names.put(name, type)
        map.put(name, value)
    }

    fun assign(name: String, value: Var<*>? = NullVar) {
        if (names.containsKey(name)) {
            values[value?.varType]?.put(name, value) ?: throw TypeCastException(name, names[name], value?.varType)
        } else {
            parent?.assign(name, value) ?: throw UnknownTokenException(name)
        }
    }

    operator fun get(name: String): Var<*> = values[names[name]]?.get(name)
            ?: parent?.get(name)
            ?: throw UnknownTokenException(name)

    fun newChildScope(): Scope = Scope(this, functions)

}