package com.troystopera.jkode.exceptions.compile

import com.troystopera.jkode.Function
import com.troystopera.jkode.exceptions.JKodeCompileException

class FunctionReturnException(
        function: Function<*>,
        override val cause: Throwable
) : JKodeCompileException(Type.FUNCTION_RETURN, "Function ${function.name} has improper or missing return statement")