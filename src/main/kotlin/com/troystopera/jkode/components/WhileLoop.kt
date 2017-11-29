package com.troystopera.jkode.components

import com.troystopera.jkode.CtrlStmt
import com.troystopera.jkode.Evaluation
import com.troystopera.jkode.exec.Executor
import com.troystopera.jkode.exec.MutableOutput
import com.troystopera.jkode.exec.Scope
import com.troystopera.jkode.vars.BooleanVar

class WhileLoop(
        val condition: Evaluation<BooleanVar>
) : CodeBlock() {

    override fun onExecute(scope: Scope, output: MutableOutput?, executor: Executor?): CtrlStmt<*>? {
        while (condition.execute(scope, output, executor).value) {
            val v = super.onExecute(scope, output, executor)
            if (v != null) return v
        }
        return null
    }

}