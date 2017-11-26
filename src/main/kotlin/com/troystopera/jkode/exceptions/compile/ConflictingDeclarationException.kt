package com.troystopera.jkode.exceptions.compile

import com.troystopera.jkode.exceptions.JKodeCompileException

class ConflictingDeclarationException(
        name: String
) : JKodeCompileException(Type.CONFLICTING_DECLARAION, "Variable $name has already been declared in this scope")