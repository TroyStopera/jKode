package com.troystopera.jkode.components

import com.troystopera.jkode.CtrlStmt
import com.troystopera.jkode.Evaluation
import com.troystopera.jkode.control.Break
import com.troystopera.jkode.control.Return
import com.troystopera.jkode.exec.Executor
import com.troystopera.jkode.exec.MutableOutput
import com.troystopera.jkode.exec.Scope
import com.troystopera.jkode.statements.Assignment
import com.troystopera.jkode.statements.Declaration
import com.troystopera.jkode.vars.BooleanVar
import com.troystopera.jkode.vars.IntVar

class ForLoop(
        val initialization: Declaration<IntVar>,
        val condition: Evaluation<BooleanVar>,
        val afterthought: Assignment
) : CodeBlock() {

    override fun onExecute(scope: Scope, output: MutableOutput?, executor: Executor?): CtrlStmt<*>? {
        initialization.execute(scope, output, executor)
        while (condition.execute(scope, output, executor).value) {
            val v = super.onExecute(scope, output, executor)
            when (v) {
                is Break -> return null
                is Return<*> -> return v
            }
            afterthought.execute(scope, output, executor)
        }
        return null
    }

}