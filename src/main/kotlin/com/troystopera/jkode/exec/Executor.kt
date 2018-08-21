package com.troystopera.jkode.exec

import com.troystopera.jkode.exceptions.JKodeCompileException
import com.troystopera.jkode.exceptions.JKodeRuntimeException
import com.troystopera.jkode.exec.override.ExecutionOverride
import com.troystopera.jkode.vars.JVar

open class Executor {

    var exceptionHandler: ExceptionHandler? = null

    private val callStack = MutableCallStack()

    open fun <T : Any?, E : Executable<T>> onPreExecute(executable: E): ExecutionOverride<T, E>? {
        callStack.startCall(executable)
        return null
    }

    open fun <T : Any?, E : Executable<T>> onPostExecute(executable: E, result: T) = callStack.endCall()

    fun currentCallStack(): CallStack = callStack

    fun execute(executable: Executable<*>): Output {
        val output = MutableOutput()
        val scope = Scope()

        val result = try {
            executable.execute(scope, this, output)
        } catch (e: JKodeCompileException) {
            exceptionHandler?.handleCompileException(e)
        } catch (e: JKodeRuntimeException) {
            exceptionHandler?.handleRuntimeException(e)
        }

        if (result is JVar<*>) output.setReturn(result)

        return output
    }

    interface ExceptionHandler {
        fun handleCompileException(exception: JKodeCompileException)
        fun handleRuntimeException(exception: JKodeRuntimeException)
    }

}