package com.troystopera.jkode.evaluations

import com.troystopera.jkode.Evaluation
import com.troystopera.jkode.exceptions.compile.TypeCastException
import com.troystopera.jkode.exceptions.runtime.ArrayIndexException
import com.troystopera.jkode.exec.Executor
import com.troystopera.jkode.exec.MutableOutput
import com.troystopera.jkode.exec.Scope
import com.troystopera.jkode.vars.IntVar
import com.troystopera.jkode.vars.JVar
import com.troystopera.jkode.vars.VarType

class ArrayAccess<out T : JVar<*>>(
        varType: VarType<T>,
        val arrayName: String,
        val index: Evaluation<IntVar>
) : Evaluation<T>(varType) {

    override fun onExecute(scope: Scope, output: MutableOutput?, executor: Executor?): T {
        val raw = scope[arrayName]
        val array = VarType.ARRAY[varType].castOrNull(raw)
                ?: throw TypeCastException(arrayName, raw.varType, VarType.ARRAY[varType])
        val index = index.execute(scope, output, executor).value
        return if (index < array.value.size) array.value[index] ?: varType.NULL
        else throw ArrayIndexException(array.value.size, index)
    }

}