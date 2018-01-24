package com.troystopera.jkode.vars

import com.troystopera.jkode.Evaluation
import com.troystopera.jkode.exec.Executor
import com.troystopera.jkode.exec.MutableOutput
import com.troystopera.jkode.exec.Scope

class JVarEvaluation<out T : JVar<*>>(val jVar: T, varType: VarType<T>) : Evaluation<T>(varType) {

    override fun onExecute(scope: Scope, output: MutableOutput?, executor: Executor?) = jVar

}