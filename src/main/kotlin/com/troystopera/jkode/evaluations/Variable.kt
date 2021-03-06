package com.troystopera.jkode.evaluations

import com.troystopera.jkode.Evaluation
import com.troystopera.jkode.exceptions.compile.TypeCastException
import com.troystopera.jkode.exec.Executor
import com.troystopera.jkode.exec.MutableOutput
import com.troystopera.jkode.exec.Scope
import com.troystopera.jkode.vars.JVar
import com.troystopera.jkode.vars.VarType

class Variable<out T : JVar<*>>(
        type: VarType<T>,
        val name: String
) : Evaluation<T>(type) {

    override fun onExecute(scope: Scope, executor: Executor, output: MutableOutput?): T {
        val value = varType.castOrNull(scope[name])
        return value ?: throw TypeCastException(name, varType, value?.getVarType())
    }

}