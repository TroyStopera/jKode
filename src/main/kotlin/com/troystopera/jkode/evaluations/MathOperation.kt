package com.troystopera.jkode.evaluations

import com.troystopera.jkode.Evaluation
import com.troystopera.jkode.exec.Executor
import com.troystopera.jkode.exec.MutableOutput
import com.troystopera.jkode.exec.Scope
import com.troystopera.jkode.vars.Var

class MathOperation<T : Any, V>(
        val type: Type,
        val leftEvaluation: Evaluation<V>,
        val rightEvaluation: Evaluation<V>
) : Evaluation<V>(leftEvaluation.varType) where V : Var<T>, V : MathOperation.Operable<V> {

    override fun onExecute(scope: Scope, output: MutableOutput?, executor: Executor?): V {
        val left = leftEvaluation.execute(scope, output, executor)
        val right = rightEvaluation.execute(scope, output, executor)
        return when (type) {
            MathOperation.Type.PLUS -> left + right
            MathOperation.Type.MINUS -> left - right
            MathOperation.Type.DIVIDE -> left / right
            MathOperation.Type.MULTIPLY -> left * right
            MathOperation.Type.MODULO -> left % right
        }
    }

    enum class Type {
        PLUS, MINUS, DIVIDE, MULTIPLY, MODULO
    }

    interface Operable<T> where T : Var<*>, T : Operable<T> {
        operator fun plus(operable: T): T
        operator fun minus(operable: T): T
        operator fun div(operable: T): T
        operator fun times(operable: T): T
        operator fun rem(operable: T): T
    }

}