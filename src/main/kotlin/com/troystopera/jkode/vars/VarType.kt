package com.troystopera.jkode.vars

import com.troystopera.jkode.util.Caster

sealed class VarType<out T : Var<*>>(
        val name: String,
        val NULL: T
) {

    override fun equals(other: Any?) =
            if (other is VarType<*>) name == other.name
            else false

    override fun hashCode() = name.hashCode()

    fun castOrNull(value: Any): T? = Caster.safeCast(NULL, value)

}

abstract class ArrayType<T : Var<*>>(name: String, NULL: ArrayVar<T>) : VarType<ArrayVar<T>>(name, NULL)

object ARRAY : ArrayType<Var<*>>("Array", ArrayVar.NULL) {
    //TODO setup array type cache
    operator fun <T : Var<*>> get(type: VarType<T>): ArrayType<T> =
            object : ArrayType<T>(type.name + "Array", ArrayVar(type, null)) {}
}

object UNIT : VarType<UnitVar>("Unit", UnitVar)

object BOOLEAN : VarType<BooleanVar>("Boolean", BooleanVar.NULL)

object INT : VarType<IntVar>("Int", IntVar.NULL)

object STRING : VarType<StringVar>("String", StringVar.NULL)
