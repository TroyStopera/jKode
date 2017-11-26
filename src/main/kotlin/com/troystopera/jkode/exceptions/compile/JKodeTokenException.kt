package com.troystopera.jkode.exceptions.compile

import com.troystopera.jkode.exceptions.JKodeCompileException
import com.troystopera.jkode.exec.CallStack

class JKodeTokenException(
        token: String,
        stackTrace: CallStack?
) : JKodeCompileException(Type.UNKNOWN_TOKEN, "Unknown token $token", stackTrace)