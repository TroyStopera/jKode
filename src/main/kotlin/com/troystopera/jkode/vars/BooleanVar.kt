package com.troystopera.jkode.vars

import com.troystopera.jkode.Evaluation
import com.troystopera.jkode.exec.Executor
import com.troystopera.jkode.exec.MutableOutput
import com.troystopera.jkode.exec.Scope

class BooleanVar private constructor(
        value: Boolean?
) : JVar<Boolean>(value) {

    override fun getVarType(): VarType<BooleanVar> = VarType.BOOLEAN

    override fun asEval(): Evaluation<BooleanVar> = object : Evaluation<BooleanVar>(getVarType()) {
        override fun onExecute(scope: Scope, output: MutableOutput?, executor: Executor?) = this@BooleanVar
    }

    companion object {

        val TRUE = BooleanVar(true)
        val FALSE = BooleanVar(false)
        val NULL = BooleanVar(null)

        operator fun get(b: Boolean) = if (b) TRUE else FALSE

    }

}