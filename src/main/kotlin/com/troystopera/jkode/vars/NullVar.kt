package com.troystopera.jkode.vars

import com.troystopera.jkode.exceptions.runtime.JKodeNullException

object NullVar : Var<Unit>(VarType.NULL, Unit) {

    override val value: Unit
        get() = throw JKodeNullException(null)

}