package com.troystopera.jkode.evaluations

import com.troystopera.jkode.Evaluation
import com.troystopera.jkode.exec.Executor
import com.troystopera.jkode.exec.MutableOutput
import com.troystopera.jkode.exec.Scope
import com.troystopera.jkode.vars.IntVar
import com.troystopera.jkode.vars.Var
import com.troystopera.jkode.vars.VarType

class ArrayAccess<T : Var<*>>(
        varType: VarType,
        val arrayName: String,
        val index: Evaluation<IntVar>
) : Evaluation<T>(varType) {

    override fun onExecute(scope: Scope, output: MutableOutput?, executor: Executor?): T {
        TODO()
    }

}