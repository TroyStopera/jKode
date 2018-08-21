package com.troystopera.jkode.statements

import com.troystopera.jkode.Evaluation
import com.troystopera.jkode.Statement
import com.troystopera.jkode.exec.MutableOutput
import com.troystopera.jkode.exec.Executor
import com.troystopera.jkode.exec.Scope
import com.troystopera.jkode.vars.JVar
import com.troystopera.jkode.vars.VarType

class Declaration<out T : JVar<*>>(
        val varType: VarType<T>,
        val varName: String,
        val initialValue: Evaluation<T>? = null
) : Statement() {

    override fun onExecute(scope: Scope, executor: Executor, output: MutableOutput?) =
            scope.declare(varType, varName, initialValue?.execute(scope, executor, output))

}