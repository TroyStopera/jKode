package com.troystopera.jkode.evaluations

import com.troystopera.jkode.Evaluation
import com.troystopera.jkode.exec.Executor
import com.troystopera.jkode.exec.MutableOutput
import com.troystopera.jkode.exec.Scope
import com.troystopera.jkode.vars.JVar

class MathOperation<T : Any, V>(
        val type: Type,
        val leftValue: Evaluation<V>,
        val rightValue: Evaluation<V>
) : Evaluation<V>(leftValue.varType) where V : JVar<T>, V : MathOperation.Operable<V> {

    override fun onExecute(scope: Scope, executor: Executor, output: MutableOutput?): V {
        val left = leftValue.execute(scope, executor, output)
        val right = rightValue.execute(scope, executor, output)
        return when (type) {
            MathOperation.Type.ADD -> left + right
            MathOperation.Type.SUBTRACT -> left - right
            MathOperation.Type.DIVIDE -> left / right
            MathOperation.Type.MULTIPLY -> left * right
            MathOperation.Type.MODULO -> left % right
        }
    }

    enum class Type {
        ADD,
        SUBTRACT,
        DIVIDE,
        MULTIPLY,
        MODULO
    }

    interface Operable<T> where T : JVar<*>, T : Operable<T> {
        operator fun plus(operable: T): T
        operator fun minus(operable: T): T
        operator fun div(operable: T): T
        operator fun times(operable: T): T
        operator fun rem(operable: T): T
    }

}