package com.troystopera.jkode.exec

import com.troystopera.jkode.exec.override.ExecutionOverride
import com.troystopera.jkode.vars.Var

open class Executor {

    open fun <T : Any?, E : Executable<T>> onPreExecute(executable: E): ExecutionOverride<T, E>? = null

    open fun <T : Any?, E : Executable<T>> onPostExecute(executable: E, result: T) = Unit

    open fun execute(executable: Executable<*>): Output {
        val output = ExecOutput()
        val scope = Scope()
        val result = executable.execute(scope, output, this)
        if (result is Var<*>)
            output.setReturn(result)
        return output
    }

}