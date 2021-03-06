package com.troystopera.jkode.statements

import com.troystopera.jkode.Evaluation
import com.troystopera.jkode.Statement
import com.troystopera.jkode.exec.MutableOutput
import com.troystopera.jkode.exec.Executor
import com.troystopera.jkode.exec.Scope

class Assignment(
        val varName: String,
        val value: Evaluation<*>
) : Statement() {

    override fun onExecute(scope: Scope, executor: Executor, output: MutableOutput?) =
            scope.assign(varName, value.execute(scope, executor, output))

}