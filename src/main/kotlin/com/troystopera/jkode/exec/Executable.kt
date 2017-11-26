package com.troystopera.jkode.exec

import com.troystopera.jkode.exceptions.JKodeException
import com.troystopera.jkode.exceptions.runtime.NullVarException

abstract class Executable<out T : Any?> {

    protected abstract fun onExecute(scope: Scope, output: MutableOutput?, executor: Executor?): T

    fun execute(scope: Scope, output: MutableOutput? = null, executor: Executor? = null): T {
        try {
            val result = executor?.onPreExecute(this)?.execute(this, scope, output, executor) ?:
                    onExecute(scope, output, executor)
            executor?.onPostExecute(this, result)
            return result
        } catch (e: JKodeException) {
            e.setStackTrace(executor?.currentCallStack())
            output?.err(e)
            throw e
        }
    }

}