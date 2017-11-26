package com.troystopera.jkode.exceptions.compile

import com.troystopera.jkode.exceptions.JKodeCompileException
import com.troystopera.jkode.exec.CallStack

class UnknownTokenException(
        token: String
) : JKodeCompileException(Type.UNKNOWN_TOKEN, "Unknown token $token")