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

    val body = CodeBlock()

    override fun onExecute(scope: Scope, output: MutableOutput?, executor: Executor?): T {
        val value = (body.execute(scope.newChildScope(), output, executor) as? Return<*>)?.data
                ?: throw FunctionReturnException(this)
        return returnType.castOrNull(value) ?: throw FunctionReturnException(this, value.varType)
    }

}