package com.troystopera.jkode

import com.troystopera.jkode.components.CodeBlock
import com.troystopera.jkode.control.Return
import com.troystopera.jkode.exceptions.compile.FunctionReturnException
import com.troystopera.jkode.exec.MutableOutput
import com.troystopera.jkode.exec.Executable
import com.troystopera.jkode.exec.Executor
import com.troystopera.jkode.exec.Scope
import com.troystopera.jkode.vars.Var
import com.troystopera.jkode.vars.VarType

class Function<out T : Var<*>>(
        val returnType: VarType<T>,
        val name: String
) : Executable<T>() {

    private val body = CodeBlock()

    fun add(executable: Executable<*>) = body.add(executable)

    override fun onExecute(scope: Scope, output: MutableOutput?, executor: Executor?): T {
        val value = (body.execute(scope.newChildScope(), output, executor) as? Return<*>)?.data
                ?: throw FunctionReturnException(this)
        return returnType.castOrNull(value) ?: throw FunctionReturnException(this, value.varType)
    }

}