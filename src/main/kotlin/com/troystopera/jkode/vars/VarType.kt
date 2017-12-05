package com.troystopera.jkode.vars

import com.troystopera.jkode.util.Caster

sealed class VarType<out T : JVar<*>>(
        val name: String,
        val NULL: T
) {

    object ARRAY : ArrayType<JVar<*>>(UNIT, "Array", ArrayVar.NULL) {
        operator fun <T : JVar<*>> get(type: VarType<T>) =
                object : ArrayType<T>(type, type.name + "Array", ArrayVar(type, null)) {}
    }

    object UNIT : VarType<UnitVar>("Unit", UnitVar)

    object BOOLEAN : VarType<BooleanVar>("Boolean", BooleanVar.NULL)

    object INT : VarType<IntVar>("Int", IntVar.NULL)

    object STRING : VarType<StringVar>("String", StringVar.NULL)

    override fun equals(other: Any?) =
            if (other is VarType<*>) name == other.name
            else false

    override fun hashCode() = name.hashCode()

    fun castOrNull(value: Any): T? = Caster.safeCast(NULL, value)

}

abstract class ArrayType<T : JVar<*>>(var elementType: VarType<*>, name: String, NULL: ArrayVar<T>) : VarType<ArrayVar<T>>(name, NULL)
