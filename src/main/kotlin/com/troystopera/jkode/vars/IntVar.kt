package com.troystopera.jkode.vars

import com.troystopera.jkode.evaluations.MathOperation

class IntVar private constructor(
        value: Int?
) : Comparable<IntVar>, MathOperation.Operable<IntVar>, JVar<Int>(value) {

    override fun getVarType(): VarType<IntVar> = VarType.INT

    override fun asEval(): JVarEvaluation<IntVar> = JVarEvaluation(this, getVarType())

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