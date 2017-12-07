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

    override fun onExecute(scope: Scope, output: MutableOutput?, executor: Executor?): BooleanVar {
        val left = leftValue.execute(scope, output, executor)
        val right = rightValue.execute(scope, output, executor)
        return when (type) {
            Comparison.Type.LESS_THAN -> BooleanVar[(left < right)]
            Comparison.Type.LESS_THAN_EQUAL_TO -> BooleanVar[(left <= right)]
            Comparison.Type.EQUAL_TO -> BooleanVar[(left == right)]
            Comparison.Type.NOT_EQUAL_TO -> BooleanVar[(left != right)]
            Comparison.Type.GREATER_THEN_EQUAL_TO -> BooleanVar[(left >= right)]
            Comparison.Type.GREATER_THAN -> BooleanVar[(left > right)]
        }
    }

    enum class Type {
        LESS_THAN,
        LESS_THAN_EQUAL_TO,
        EQUAL_TO,
        NOT_EQUAL_TO,
        GREATER_THEN_EQUAL_TO,
        GREATER_THAN
    }

}