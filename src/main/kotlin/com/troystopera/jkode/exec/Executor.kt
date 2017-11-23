package com.troystopera.jkode.exec

import com.troystopera.jkode.exceptions.JKodeException
import com.troystopera.jkode.exec.override.ExecutionOverride
import com.troystopera.jkode.vars.Var

open class Executor {

    var exceptionHandler: ExceptionHandler? = null

    open fun <T : Any?, E : Executable<T>> onPreExecute(executable: E): ExecutionOverride<T, E>? = null

    open fun <T : Any?, E : Executable<T>> onPostExecute(executable: E, result: T) = Unit

    open fun execute(executable: Executable<*>): Output {
        val output = ExecOutput()
        val scope = Scope()

        val result = try {
            executable.execute(scope, output, this)
        } catch (e: JKodeException) {
            exceptionHandler?.handleException(e)
        }

        if (result is Var<*>)
            output.setReturn(result)

        return output
    }

    interface ExceptionHandler {
        fun handleException(exception: JKodeException)
    }

}