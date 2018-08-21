package com.troystopera.jkode.statements

import com.troystopera.jkode.Evaluation
import com.troystopera.jkode.Statement
import com.troystopera.jkode.exec.Executor
import com.troystopera.jkode.exec.MutableOutput
import com.troystopera.jkode.exec.Scope
import com.troystopera.jkode.vars.ArrayVar
import com.troystopera.jkode.vars.IntVar
import com.troystopera.jkode.vars.JVar

class ArrayAssign<T : JVar<*>>(
        val array: Evaluation<ArrayVar<T>>,
        val index: Evaluation<IntVar>,
        val value: Evaluation<T>
) : Statement() {

    override fun onExecute(scope: Scope, executor: Executor, output: MutableOutput?) = array.execute(scope, executor, output)
            .set(index.execute(scope, executor, output).value, value.execute(scope, executor, output))

}