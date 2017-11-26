package com.troystopera.jkode.vars

import com.troystopera.jkode.evaluations.MathOperation

class IntVar private constructor(
        value: Int
) : Comparable<IntVar>, MathOperation.Operable<IntVar>, Var<Int>(VarType.INT, value) {

    override fun plus(operable: IntVar) = IntVar[value + operable.value]

    override fun minus(operable: IntVar) = IntVar[value - operable.value]

    override fun div(operable: IntVar) = IntVar[value / operable.value]

    override fun times(operable: IntVar) = IntVar[value * operable.value]

    override fun rem(operable: IntVar) = IntVar[value % operable.value]

    override fun compareTo(other: IntVar) = value - other.value

    companion object {
        //TODO implement IntVar cache
        operator fun get(int: Int) = IntVar(int)
    }

}