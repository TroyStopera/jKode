package com.troystopera.jkode.exceptions.compile

import com.troystopera.jkode.exceptions.JKodeCompileException
import com.troystopera.jkode.exec.CallStack
import com.troystopera.jkode.vars.VarType

class TypeCastException(
        variable: String,
        type1: VarType?,
        type2: VarType?,
        override val cause: Throwable? = null
) : JKodeCompileException(Type.TYPE_CAST, "Variable $variable:$type1 cannot be assigned to $type2")