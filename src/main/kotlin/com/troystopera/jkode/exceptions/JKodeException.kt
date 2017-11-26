package com.troystopera.jkode.exceptions

import com.troystopera.jkode.exec.CallStack

abstract class JKodeException(
        override val message: String,
        open val stackTrace: CallStack?
) : Exception()