package com.troystopera.jkode

import com.troystopera.jkode.control.CtrlObject
import com.troystopera.jkode.exceptions.compile.FunctionReturnException
import com.troystopera.jkode.exec.MutableOutput
import com.troystopera.jkode.exec.Executable
import com.troystopera.jkode.exec.Executor
import com.troystopera.jkode.exec.Scope
import com.troystopera.jkode.vars.Var
import com.troystopera.jkode.vars.VarType

class Function<out T : Var<*>>(
        val returnType: VarType,
        val name: String
) : Executable<T?>() {

    private val body = CodeBlock()

    fun add(executable: Executable<*>) = body.add(executable)

    //TODO test execution of functions with improper return types
    override fun onExecute(scope: Scope, output: MutableOutput?, executor: Executor?): T? {
        try {
            @Suppress("UNCHECKED_CAST")
            return (body.execute(scope.newChildScope(), output, executor) as CtrlObject<T>).value
        } catch (e: Exception) {
            throw FunctionReturnException(this, executor?.currentCallStack(), e)
        }
    }

}