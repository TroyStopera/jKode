package com.troystopera.jkode.evaluations

import com.troystopera.jkode.Evaluation
import com.troystopera.jkode.exceptions.compile.TypeCastException
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

    override fun onExecute(scope: Scope, output: MutableOutput?, executor: Executor?): T {
        val arrVar = array.execute(scope, output, executor)
        val index = index.execute(scope, output, executor).value
        return if (index < arrVar.value.size) arrVar.value[index] ?: varType.NULL
        else throw ArrayIndexException(arrVar.value.size, index)
    }

}