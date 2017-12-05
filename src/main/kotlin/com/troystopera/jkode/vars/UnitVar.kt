package com.troystopera.jkode.vars

import com.troystopera.jkode.Evaluation
import com.troystopera.jkode.exec.Executor
import com.troystopera.jkode.exec.MutableOutput
import com.troystopera.jkode.exec.Scope

object UnitVar : JVar<Unit>(VarType.UNIT, null) {

    override val eval: Evaluation<UnitVar> = object : Evaluation<UnitVar>(VarType.UNIT) {
        override fun onExecute(scope: Scope, output: MutableOutput?, executor: Executor?) = this@UnitVar
    }

}