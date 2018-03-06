package com.troystopera.jkode

import com.troystopera.jkode.components.CodeBlock
import com.troystopera.jkode.control.Return
import com.troystopera.jkode.exceptions.compile.FunctionReturnException
import com.troystopera.jkode.exec.MutableOutput
import com.troystopera.jkode.exec.Executable
import com.troystopera.jkode.exec.Executor
import com.troystopera.jkode.exec.Scope
import com.troystopera.jkode.vars.JVar
import com.troystopera.jkode.vars.VarType

class JFunction<out T : JVar<*>>(
        val returnType: VarType<T>,
        val name: String
) : Executable<T>() {

    val body: CodeBlock = Body()

    override fun onExecute(scope: Scope, output: MutableOutput?, executor: Executor?): T {
        val funScope = scope.newChildScope()
        val value = (body.execute(funScope, output, executor) as? Return<*>)?.data?.execute(funScope, output, executor)
                ?: throw FunctionReturnException(this)
        return returnType.castOrNull(value) ?: throw FunctionReturnException(this, returnType, value.getVarType())
    }

    private class Body : CodeBlock() {
        override fun onExecute(scope: Scope, output: MutableOutput?, executor: Executor?) = executeBody(scope, output, executor)
    }

}