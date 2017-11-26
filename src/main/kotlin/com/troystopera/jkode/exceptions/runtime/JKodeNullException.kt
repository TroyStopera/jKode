package com.troystopera.jkode.exceptions.runtime

import com.troystopera.jkode.exceptions.JKodeRuntimeException
import com.troystopera.jkode.exec.CallStack

class JKodeNullException(
        override var stackTrace: CallStack?
) : JKodeRuntimeException(Type.NULL_POINTER, "Null pointer exception", stackTrace)