package com.troystopera.jkode.statements

import com.troystopera.jkode.Evaluation
import com.troystopera.jkode.Statement
import com.troystopera.jkode.exec.MutableOutput
import com.troystopera.jkode.exec.Executor
import com.troystopera.jkode.exec.Scope
import com.troystopera.jkode.vars.VarType

class Declaration private constructor(
        val varType: VarType,
        val varName: String,
        private val initialValue: Evaluation<*>?
) : Statement() {

    constructor(type: VarType, name: String) : this(type, name, null)

    constructor(name: String, initialValue: Evaluation<*>) : this(initialValue.varType, name, initialValue)

    override fun onExecute(scope: Scope, output: MutableOutput?, executor: Executor?) =
            scope.declare(varType, varName, initialValue?.execute(scope, output, executor), executor)

}