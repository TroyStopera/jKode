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

/**
 * A class representing a function in jKode. Executes a set of [Executable] and returns a [JVar].
 *
 * @param T the type of [JVar] that this function should return.
 * @property returnType the [VarType] of the returned [JVar].
 * @property name the name of the function.
 * @constructor Creates a new function called [name] that returns a [JVar] of type [T].
 */
class JFunction<out T : JVar<*>>(
        val returnType: VarType<T>,
        val name: String
) : Executable<T>() {

    /**
     * The [CodeBlock] that contains all [Executable]s within this function.
     */
    val body = object : CodeBlock() {
        override fun onExecute(scope: Scope, executor: Executor, output: MutableOutput?) = executeBody(scope, executor, output)
    }

    /**
     * Executes all items in the [body] and watches for a [Return] value to return.
     */
    override fun onExecute(scope: Scope, executor: Executor, output: MutableOutput?): T {
        val funScope = scope.newChildScope()
        val value = (body.execute(funScope, executor, output) as? Return<*>)?.data?.execute(funScope, executor, output)
                ?: throw FunctionReturnException(this)
        return returnType.castOrNull(value) ?: throw FunctionReturnException(this, returnType, value.getVarType())
    }

}