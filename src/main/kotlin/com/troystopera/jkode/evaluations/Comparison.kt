package com.troystopera.jkode.evaluations

import com.troystopera.jkode.Evaluation
import com.troystopera.jkode.exec.Executor
import com.troystopera.jkode.exec.MutableOutput
import com.troystopera.jkode.exec.Scope
import com.troystopera.jkode.vars.BooleanVar
import com.troystopera.jkode.vars.JVar
import com.troystopera.jkode.vars.VarType

class Comparison<T>(
        val type: Type,
        val leftValue: Evaluation<T>,
        val rightValue: Evaluation<T>
) : Evaluation<BooleanVar>(VarType.BOOLEAN) where T : JVar<*>, T : Comparable<T> {

    override fun onExecute(scope: Scope, executor: Executor, output: MutableOutput?): BooleanVar {
        val left = leftValue.execute(scope, executor, output)
        val right = rightValue.execute(scope, executor, output)
        return when (type) {
            Comparison.Type.LESS_THAN -> BooleanVar[(left < right)]
            Comparison.Type.LESS_THAN_EQUAL_TO -> BooleanVar[(left <= right)]
            Comparison.Type.EQUAL_TO -> BooleanVar[(left == right)]
            Comparison.Type.NOT_EQUAL_TO -> BooleanVar[(left != right)]
            Comparison.Type.GREATER_THAN_EQUAL_TO -> BooleanVar[(left >= right)]
            Comparison.Type.GREATER_THAN -> BooleanVar[(left > right)]
        }
    }

    fun withCompType(type: Comparison.Type): Comparison<T> = Comparison(type, leftValue, rightValue)

    enum class Type {
        LESS_THAN,
        LESS_THAN_EQUAL_TO,
        EQUAL_TO,
        NOT_EQUAL_TO,
        GREATER_THAN_EQUAL_TO,
        GREATER_THAN
    }

}