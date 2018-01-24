package com.troystopera.jkode.vars

object UnitVar : JVar<Unit>(null) {

    override fun getVarType(): VarType<UnitVar> = VarType.UNIT

    override fun asEval(): JVarEvaluation<UnitVar> = JVarEvaluation(this, getVarType())

}