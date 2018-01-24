package com.troystopera.jkode.vars

import com.troystopera.jkode.Evaluation
import com.troystopera.jkode.exec.Executor
import com.troystopera.jkode.exec.MutableOutput
import com.troystopera.jkode.exec.Scope

object UnitVar : JVar<Unit>(null) {

    override fun getVarType(): VarType<UnitVar> = VarType.UNIT

    override fun asEval(): Evaluation<UnitVar> = object : Evaluation<UnitVar>(getVarType()) {
        override fun onExecute(scope: Scope, output: MutableOutput?, executor: Executor?) = this@UnitVar
    }

}