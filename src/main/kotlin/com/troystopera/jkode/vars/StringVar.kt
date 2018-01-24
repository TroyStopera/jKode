package com.troystopera.jkode.vars

import com.troystopera.jkode.Evaluation
import com.troystopera.jkode.exec.Executor
import com.troystopera.jkode.exec.MutableOutput
import com.troystopera.jkode.exec.Scope

class StringVar private constructor(
        value: String?
) : JVar<String>(value) {

    override fun getVarType(): VarType<StringVar> = VarType.STRING

    override fun asEval(): Evaluation<StringVar> = object : Evaluation<StringVar>(getVarType()) {
        override fun onExecute(scope: Scope, output: MutableOutput?, executor: Executor?) = this@StringVar
    }

    companion object {
        val NULL = StringVar(null)
        //TODO implement string var cache
        operator fun get(string: String) = StringVar(string)
    }

}