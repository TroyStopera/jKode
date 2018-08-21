package com.troystopera.jkode.exec

import com.troystopera.jkode.exceptions.JKodeException
import com.troystopera.jkode.exceptions.runtime.NullVarException

/**
 * A very abstract class used to represent anything that can be virtually executed as code.
 * @param T the type of whatever this Executable will return when called at virtual runtime.
 */
abstract class Executable<out T : Any?> {

    /**
     * Contains the logic of this Executable.
     *
     * @param scope the [Scope] that this executable should use as reference.
     * @param executor the [Executor] that should be notified of this Executable's execution.
     * @param output the [Output] object that should be written to during virtual runtime.
     * @return any object of type [T].     *
     */
    protected abstract fun onExecute(scope: Scope, executor: Executor, output: MutableOutput?): T

    /**
     * Executes this class' [onExecute] method on the given [Scope].
     *
     * It takes into account logging errors and is ran through an [Executor].
     *
     * Always call this when executing an executable and never [onExecute]!
     *
     * @param scope the [Scope] that this executable should use as reference.
     * @param executor the [Executor] that should be notified of this Executable's execution.
     * @param output the [Output] object that should be written to during virtual runtime (optional).
     * @return any object of type [T].
     */
    fun execute(scope: Scope, executor: Executor, output: MutableOutput? = null): T {
        try {
            val result = executor.onPreExecute(this)?.execute(this, scope, executor, output)
                    ?: onExecute(scope, executor, output)
            executor.onPostExecute(this, result)
            return result
        } catch (e: JKodeException) {
            e.setStackTrace(executor.currentCallStack())
            output?.err(e)
            throw e
        }
    }

}