package com.troystopera.jkode.exec

import com.troystopera.jkode.exceptions.JKodeCompileException
import com.troystopera.jkode.exceptions.JKodeException
import com.troystopera.jkode.exceptions.JKodeRuntimeException
import com.troystopera.jkode.vars.Var

class MutableOutput : Output {

    private val console = mutableListOf<String>()
    private var returnVar: Var<*>? = null
    private var compileException: JKodeCompileException? = null
    private var runtimeException: JKodeRuntimeException? = null

    override fun getConsole() = console

    override fun getReturnVar() = returnVar

    override fun getCompileException() = compileException

    override fun getRuntimeException() = runtimeException

    fun print(string: String) {
        console.add(string)
    }

    fun err(exception: JKodeException) {
        if (exception is JKodeCompileException) {
            compileException = exception
        } else if (exception is JKodeRuntimeException) {
            runtimeException = exception
            var string = (exception::class.simpleName ?: "JKodeException") + ": " + exception.message +
                    if (exception.getStackTrace() != null) " @ " + exception.getStackTrace()?.getBase()
                    else ""
            exception.getStackTrace()?.getCallers()?.forEach { string += "\n\t@" + it }
            print(string)
        }
    }

    fun setReturn(retVar: Var<*>?) {
        returnVar = retVar
    }

}