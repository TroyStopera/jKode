package com.troystopera.jkode.evaluations

import com.troystopera.jkode.Evaluation
import com.troystopera.jkode.exceptions.runtime.ArrayIndexException
import com.troystopera.jkode.exec.Executor
import com.troystopera.jkode.exec.MutableOutput
import com.troystopera.jkode.exec.Scope
import com.troystopera.jkode.vars.ArrayVar
import com.troystopera.jkode.vars.IntVar
import com.troystopera.jkode.vars.JVar
import com.troystopera.jkode.vars.VarType

class ArrayAccess<T : JVar<*>>(
        varType: VarType<T>,
        val array: Evaluation<ArrayVar<T>>,
        val index: Evaluation<IntVar>
) : Evaluation<T>(varType) {

    override fun onExecute(scope: Scope, executor: Executor, output: MutableOutput?): T {
        val arrVar = array.execute(scope, executor, output)
        val index = index.execute(scope, executor, output).value
        return if (index < arrVar.value.size) arrVar.value[index] ?: varType.NULL
        else throw ArrayIndexException(arrVar.value.size, index)
    }

}