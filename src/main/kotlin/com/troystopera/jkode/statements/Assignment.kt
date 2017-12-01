package com.troystopera.jkode.statements

import com.troystopera.jkode.Evaluation
import com.troystopera.jkode.Statement
import com.troystopera.jkode.exec.MutableOutput
import com.troystopera.jkode.exec.Executor
import com.troystopera.jkode.exec.Scope
import com.troystopera.jkode.vars.VarType

class Assignment(
        val varName: String,
        val evaluation: Evaluation<*>
) : Statement() {

    override fun onExecute(scope: Scope, output: MutableOutput?, executor: Executor?) =
            scope.assign(varName, evaluation.execute(scope, output, executor))

}