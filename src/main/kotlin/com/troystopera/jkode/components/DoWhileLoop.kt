package com.troystopera.jkode.components

import com.troystopera.jkode.CtrlStmt
import com.troystopera.jkode.Evaluation
import com.troystopera.jkode.control.Break
import com.troystopera.jkode.control.Continue
import com.troystopera.jkode.control.Return
import com.troystopera.jkode.exec.Executor
import com.troystopera.jkode.exec.MutableOutput
import com.troystopera.jkode.exec.Scope
import com.troystopera.jkode.vars.BooleanVar

class DoWhileLoop(
        val condition: Evaluation<BooleanVar>
) : CodeBlock() {

    override fun onExecute(scope: Scope, output: MutableOutput?, executor: Executor?): CtrlStmt<*>? {
        var v = super.onExecute(scope, output, executor)
        when (v) {
            is Break -> return null
            is Return<*> -> return v
        }

        while (condition.execute(scope, output, executor).value) {
            v = super.onExecute(scope, output, executor)
            when (v) {
                is Break -> return null
                is Return<*> -> return v
            }
        }

        return null
    }

}