package com.troystopera.jkode.exec

import com.troystopera.jkode.exceptions.JKodeCompileException
import com.troystopera.jkode.exceptions.JKodeRuntimeException
import com.troystopera.jkode.vars.Var

interface Output {

    fun getConsole(): List<String>

    fun getReturnVar(): Var<*>?

    fun getCompileException(): JKodeCompileException?

    fun getRuntimeException(): JKodeRuntimeException?

}