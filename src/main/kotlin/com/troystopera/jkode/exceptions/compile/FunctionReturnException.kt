package com.troystopera.jkode.exceptions.compile

import com.troystopera.jkode.JFunction
import com.troystopera.jkode.exceptions.JKodeCompileException
import com.troystopera.jkode.vars.VarType

class FunctionReturnException : JKodeCompileException {

    constructor(JFunction: JFunction<*>, improperType: VarType<*>?) :
            super(Type.FUNCTION_RETURN, "Function ${JFunction.name} has improper return type ($improperType)")

    constructor(JFunction: JFunction<*>) :
            super(Type.FUNCTION_RETURN, "Function ${JFunction.name} is missing a return statement")

}