package com.troystopera.jkode.control

import com.troystopera.jkode.Evaluation
import com.troystopera.jkode.exec.ExecOutput
import com.troystopera.jkode.exec.Executable
import com.troystopera.jkode.exec.Executor
import com.troystopera.jkode.exec.Scope
import com.troystopera.jkode.vars.Var

class Return<out T : Var<*>>(
        private val evaluation: Evaluation<T>
) : Executable<CtrlObject<T>>() {

    override fun onExecute(scope: Scope, output: ExecOutput?, executor: Executor?) =
            CtrlObject(CtrlType.RETURN, evaluation.execute(scope, output, executor))

}