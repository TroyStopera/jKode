package com.troystopera.jkode.evaluations

import com.troystopera.jkode.Evaluation
import com.troystopera.jkode.exceptions.compile.TypeCastException
import com.troystopera.jkode.exec.Executor
import com.troystopera.jkode.exec.MutableOutput
import com.troystopera.jkode.exec.Scope
import com.troystopera.jkode.vars.*

class ArrayLength(
        val array: Evaluation<ArrayVar<*>>
) : Evaluation<IntVar>(VarType.INT) {

    override fun onExecute(scope: Scope, executor: Executor, output: MutableOutput?) =
            IntVar[array.execute(scope, executor, output).value.size]

}