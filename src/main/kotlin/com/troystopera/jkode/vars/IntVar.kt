package com.troystopera.jkode.vars

import com.troystopera.jkode.Evaluation
import com.troystopera.jkode.evaluations.MathOperation
import com.troystopera.jkode.exec.Executor
import com.troystopera.jkode.exec.MutableOutput
import com.troystopera.jkode.exec.Scope

class IntVar private constructor(
        value: Int?
) : Comparable<IntVar>, MathOperation.Operable<IntVar>, JVar<Int>(VarType.INT, value) {

    override val eval: Evaluation<IntVar> = object : Evaluation<IntVar>(VarType.INT) {
        override fun onExecute(scope: Scope, output: MutableOutput?, executor: Executor?) = this@IntVar
    }

    override fun plus(operable: IntVar) = IntVar[value + operable.value]

    override fun minus(operable: IntVar) = IntVar[value - operable.value]

    override fun div(operable: IntVar) = IntVar[value / operable.value]

    override fun times(operable: IntVar) = IntVar[value * operable.value]

    override fun rem(operable: IntVar) = IntVar[value % operable.value]

    override fun compareTo(other: IntVar) = value - other.value

    companion object {
        val NULL = IntVar(null)

        //TODO implement IntVar cache
        operator fun get(int: Int) = IntVar(int)
    }

}