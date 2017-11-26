package com.troystopera.jkode.exec

import com.troystopera.jkode.exceptions.JKodeException
import com.troystopera.jkode.exceptions.runtime.JKodeNullException

abstract class Executable<out T : Any?> {

    protected abstract fun onExecute(scope: Scope, output: MutableOutput?, executor: Executor?): T

    fun execute(scope: Scope, output: MutableOutput? = null, executor: Executor? = null): T {
        try {
            val result = executor?.onPreExecute(this)?.execute(this, scope, output, executor) ?:
                    onExecute(scope, output, executor)
            executor?.onPostExecute(this, result)
            return result
        } catch (e: JKodeException) {
            //NPE needs its stack trace set due to the nature of when it is thrown
            if (e is JKodeNullException) e.stackTrace = executor?.currentCallStack()
            output?.err(e)
            throw e
        }
    }

}