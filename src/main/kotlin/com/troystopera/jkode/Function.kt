package com.troystopera.jkode

import com.troystopera.jkode.control.CtrlObject
import com.troystopera.jkode.control.Return
import com.troystopera.jkode.exceptions.JKodeCompileException
import com.troystopera.jkode.exec.ExecOutput
import com.troystopera.jkode.exec.Executable
import com.troystopera.jkode.exec.Executor
import com.troystopera.jkode.exec.Scope
import com.troystopera.jkode.vars.Var

class Function<out T : Var<*>> : Executable<T>() {

    private val body = CodeBlock()

    fun add(executable: Executable<*>) = body.add(executable)

    //TODO test execution of functions with improper return types
    override fun onExecute(scope: Scope, output: ExecOutput?, executor: Executor?): T {
        try {
            @Suppress("UNCHECKED_CAST")
            return (body.execute(scope.newChildScope(), output, executor) as CtrlObject<T>).value
        } catch (e: Exception) {
            val exception = JKodeCompileException("Error with function return", e)
            output?.err(exception.message ?: exception.toString())
            throw exception
        }
    }

}