package com.troystopera.jkode

import com.troystopera.jkode.exec.ExecOutput
import com.troystopera.jkode.exec.Executable
import com.troystopera.jkode.exec.Executor
import com.troystopera.jkode.exec.Scope
import com.troystopera.jkode.vars.Var

class Return<out T : Var<*>>(
        private val evaluation: Evaluation<T>
) : Executable<T>() {

    override fun onExecute(scope: Scope, output: ExecOutput?, executor: Executor?) = evaluation.execute(scope, output, executor)

}