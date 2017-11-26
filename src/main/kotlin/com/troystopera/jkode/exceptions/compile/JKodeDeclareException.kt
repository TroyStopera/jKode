package com.troystopera.jkode.exceptions.compile

import com.troystopera.jkode.exceptions.JKodeCompileException
import com.troystopera.jkode.exec.CallStack

class JKodeDeclareException(
        name: String,
        stackTrace: CallStack?
) : JKodeCompileException(Type.ALREADY_DECLARED, "Variable $name has already been declared in this scope", stackTrace)