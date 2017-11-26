package com.troystopera.jkode.statements

import com.troystopera.jkode.Evaluation
import com.troystopera.jkode.Statement
import com.troystopera.jkode.exec.MutableOutput
import com.troystopera.jkode.exec.Executor
import com.troystopera.jkode.exec.Scope
import com.troystopera.jkode.vars.VarType

class Assignment private constructor(
        val varType: VarType,
        val varName: String,
        private val evaluation: Evaluation<*>
) : Statement() {

    constructor(varName: String, evaluation: Evaluation<*>) : this(evaluation.varType, varName, evaluation)

    override fun onExecute(scope: Scope, output: MutableOutput?, executor: Executor?) =
            scope.assign(varType, varName, evaluation.execute(scope, output, executor), executor)

}