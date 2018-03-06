package com.troystopera.jkode.format

import com.troystopera.jkode.*
import com.troystopera.jkode.JFunction
import com.troystopera.jkode.evaluations.Comparison
import com.troystopera.jkode.evaluations.MathOperation
import com.troystopera.jkode.exec.Executable
import com.troystopera.jkode.vars.JVar
import com.troystopera.jkode.vars.JVarEvaluation
import com.troystopera.jkode.vars.VarType

abstract class CodeFormat {

    abstract fun formatComponent(component: Component, indent: String): String

    fun formatComponent(component: Component) = formatComponent(component, "")

    abstract fun formatControlStmt(ctrlStmt: CtrlStmt<*>, indent: String): String

    fun formatControlStmt(ctrlStmt: CtrlStmt<*>) = formatControlStmt(ctrlStmt, "")

    abstract fun formatEvaluation(evaluation: Evaluation<*>, indent: String): String

    fun formatEvaluation(evaluation: Evaluation<*>) = formatEvaluation(evaluation, "")

    abstract fun formatFunction(jFunction: JFunction<*>, indent: String): String

    fun formatFunction(JFunction: JFunction<*>) = formatFunction(JFunction, "")

    abstract fun formatStatement(statement: Statement, indent: String): String

    fun formatStatement(statement: Statement) = formatStatement(statement, "")

    abstract fun formatVar(v: JVar<*>?, indent: String): String

    fun formatVar(v: JVar<*>?) = formatVar(v, "")

    abstract fun formatVarType(type: VarType<*>, indent: String = ""): String

    fun formatVarType(type: VarType<*>) = formatVarType(type, "")

    fun format(executable: Executable<*>, indent: String): String = when (executable) {
        is BlankLine -> "\n"
        is Component -> formatComponent(executable, indent)
        is CtrlStmt<*> -> formatControlStmt(executable, indent)
        is Evaluation<*> -> formatEvaluation(executable, indent)
        is JFunction<*> -> formatFunction(executable, indent)
        is Statement -> formatStatement(executable, indent)
        is JVar<*> -> formatVar(executable, indent)
        is JVarEvaluation<*> -> formatVar(executable.jVar, indent)
        else -> "${indent}Unknown executable ${executable::class.simpleName}"
    }

    fun format(executable: Executable<*>) = format(executable, "")

    open fun formatOperationType(type: MathOperation.Type): String = when (type) {
        MathOperation.Type.ADD -> "+"
        MathOperation.Type.SUBTRACT -> "-"
        MathOperation.Type.DIVIDE -> "/"
        MathOperation.Type.MULTIPLY -> "*"
        MathOperation.Type.MODULO -> "%"
    }

    open fun formatComparisonType(type: Comparison.Type): String = when (type) {
        Comparison.Type.LESS_THAN -> "<"
        Comparison.Type.LESS_THAN_EQUAL_TO -> "<="
        Comparison.Type.EQUAL_TO -> "=="
        Comparison.Type.NOT_EQUAL_TO -> "!="
        Comparison.Type.GREATER_THAN_EQUAL_TO -> ">="
        Comparison.Type.GREATER_THAN -> ">"
    }

    companion object {
        const val TAB = "    "
    }

}