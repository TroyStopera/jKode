package com.troystopera.jkode.vars

class StringVar private constructor(
        value: String?
) : JVar<String>(value) {

    override fun getVarType(): VarType<StringVar> = VarType.STRING

    override fun asEval(): JVarEvaluation<StringVar> = JVarEvaluation(this, getVarType())

    companion object {
        val NULL = StringVar(null)
        //TODO implement string var cache
        operator fun get(string: String) = StringVar(string)
    }

}