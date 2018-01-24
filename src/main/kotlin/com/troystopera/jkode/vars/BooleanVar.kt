package com.troystopera.jkode.vars

class BooleanVar private constructor(
        value: Boolean?
) : JVar<Boolean>(value) {

    override fun getVarType(): VarType<BooleanVar> = VarType.BOOLEAN

    override fun asEval(): JVarEvaluation<BooleanVar> = JVarEvaluation(this, getVarType())

    companion object {

        val TRUE = BooleanVar(true)
        val FALSE = BooleanVar(false)
        val NULL = BooleanVar(null)

        operator fun get(b: Boolean) = if (b) TRUE else FALSE

    }

}