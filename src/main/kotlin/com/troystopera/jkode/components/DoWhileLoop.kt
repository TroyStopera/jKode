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
        var condition: Evaluation<BooleanVar>
) : CodeBlock() {

    override fun onExecute(scope: Scope, executor: Executor, output: MutableOutput?): CtrlStmt<*>? {
        var v = executeBody(scope, executor, output)
        when (v) {
            Break -> return null
            is Return<*> -> return v
        }

        while (condition.execute(scope, executor, output).value) {
            v = executeBody(scope, executor, output)
            when (v) {
                Break -> return null
                is Return<*> -> return v
            }
        }

        return null
    }

}