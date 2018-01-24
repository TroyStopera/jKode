package com.troystopera.jkode.exceptions.compile

import com.troystopera.jkode.JFunction
import com.troystopera.jkode.exceptions.JKodeCompileException
import com.troystopera.jkode.vars.VarType

class FunctionReturnException : JKodeCompileException {

    constructor(JFunction: JFunction<*>, expectedType: VarType<*>, improperType: VarType<*>?) :
            super(Type.FUNCTION_RETURN, "Function ${JFunction.name} has improper return type. Expected ${expectedType.name}, Found ${improperType?.name}")

    constructor(JFunction: JFunction<*>) :
            super(Type.FUNCTION_RETURN, "Function ${JFunction.name} is missing a return statement")

}