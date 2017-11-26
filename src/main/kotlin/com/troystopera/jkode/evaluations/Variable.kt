package com.troystopera.jkode.evaluations

import com.troystopera.jkode.Evaluation
import com.troystopera.jkode.exceptions.compile.TypeCastException
import com.troystopera.jkode.exec.Executor
import com.troystopera.jkode.exec.MutableOutput
import com.troystopera.jkode.exec.Scope
import com.troystopera.jkode.vars.Var
import com.troystopera.jkode.vars.VarType

class Variable<out T : Var<*>>(
        type: VarType,
        val name: String
) : Evaluation<T>(type) {

    override fun onExecute(scope: Scope, output: MutableOutput?, executor: Executor?): T {
        val value = scope[name]
        try {
            @Suppress("UNCHECKED_CAST")
            return value as T
        } catch (e: Exception) {
            throw TypeCastException(name, varType, value?.varType, e)
        }
    }

}