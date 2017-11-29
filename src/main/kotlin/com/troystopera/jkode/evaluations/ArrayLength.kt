package com.troystopera.jkode.evaluations

import com.troystopera.jkode.Evaluation
import com.troystopera.jkode.exceptions.compile.TypeCastException
import com.troystopera.jkode.exec.Executor
import com.troystopera.jkode.exec.MutableOutput
import com.troystopera.jkode.exec.Scope
import com.troystopera.jkode.vars.*

class ArrayLength(
        val arrayName: String
) : Evaluation<IntVar>(INT) {

    override fun onExecute(scope: Scope, output: MutableOutput?, executor: Executor?): IntVar {
        val raw = scope[arrayName]
        val array = raw as? ArrayVar<*>
        return IntVar[array?.value?.size ?: throw TypeCastException(arrayName, raw.varType, ARRAY)]
    }

}