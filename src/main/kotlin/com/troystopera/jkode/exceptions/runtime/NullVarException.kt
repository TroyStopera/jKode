package com.troystopera.jkode.exceptions.runtime

import com.troystopera.jkode.exceptions.JKodeRuntimeException

class NullVarException : JKodeRuntimeException(Type.NULL_VAR, "Null pointer exception")