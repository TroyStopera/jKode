package com.troystopera.jkode.components

import com.troystopera.jkode.Evaluation
import com.troystopera.jkode.control.CtrlObject
import com.troystopera.jkode.exec.Executor
import com.troystopera.jkode.exec.MutableOutput
import com.troystopera.jkode.exec.Scope
import com.troystopera.jkode.vars.BooleanVar

class DoWhileLoop(
        val condition: Evaluation<BooleanVar>
) : CodeBlock() {

    override fun onExecute(scope: Scope, output: MutableOutput?, executor: Executor?): CtrlObject<*>? {
        var v = super.onExecute(scope, output, executor)
        if (v != null) return v
        while (condition.execute(scope, output, executor).value) {
            v = super.onExecute(scope, output, executor)
            if (v != null) return v
        }
        return null
    }

}