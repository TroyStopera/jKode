package com.troystopera.jkode.exceptions.compile

import com.troystopera.jkode.Function
import com.troystopera.jkode.exceptions.JKodeCompileException
import com.troystopera.jkode.vars.VarType

class FunctionReturnException : JKodeCompileException {

    constructor(function: Function<*>, improperType: VarType<*>?) :
            super(Type.FUNCTION_RETURN, "Function ${function.name} has improper return type ($improperType)")

    constructor(function: Function<*>) :
            super(Type.FUNCTION_RETURN, "Function ${function.name} is missing a return statement")

}