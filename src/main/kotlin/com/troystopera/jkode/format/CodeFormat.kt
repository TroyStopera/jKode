package com.troystopera.jkode.format

import com.troystopera.jkode.*
import com.troystopera.jkode.Function
import com.troystopera.jkode.evaluations.Comparison
import com.troystopera.jkode.evaluations.MathOperation
import com.troystopera.jkode.exec.Executable
import com.troystopera.jkode.vars.Var
import com.troystopera.jkode.vars.VarType

abstract class CodeFormat {

    abstract fun formatComponent(component: Component, indent: String = ""): String

    abstract fun formatControlStmt(ctrlStmt: CtrlStmt<*>, indent: String = ""): String

    abstract fun formatEvaluation(evaluation: Evaluation<*>, indent: String = ""): String

    abstract fun formatFunction(function: Function<*>, indent: String = ""): String

    abstract fun formatStatement(statement: Statement, indent: String = ""): String

    abstract fun formatVar(v: Var<*>?, indent: String = ""): String

    abstract fun formatVarType(type: VarType<*>, indent: String = ""): String

    open fun format(executable: Executable<*>, indent: String = ""): String = when (executable) {
        is BlankLine -> "\n"
        is Component -> formatComponent(executable, indent)
        is CtrlStmt<*> -> formatControlStmt(executable, indent)
        is Evaluation<*> -> formatEvaluation(executable, indent)
        is Function<*> -> formatFunction(executable, indent)
        is Statement -> formatStatement(executable, indent)
        is Var<*> -> formatVar(executable, indent)
        else -> "${indent}Unknown executable ${executable::class.simpleName}"
    }

    open fun formatOperationType(type: MathOperation.Type): String = when (type) {
        MathOperation.Type.PLUS -> "+"
        MathOperation.Type.MINUS -> "-"
        MathOperation.Type.DIVIDE -> "/"
        MathOperation.Type.MULTIPLY -> "*"
        MathOperation.Type.MODULO -> "%"
    }

    open fun formatComparisonType(type: Comparison.Type): String = when (type) {
        Comparison.Type.LESS_THAN -> "<"
        Comparison.Type.LESS_THAN_EQUAL_TO -> "<="
        Comparison.Type.EQUAL_TO -> "=="
        Comparison.Type.NOT_EQUAL_TO -> "!="
        Comparison.Type.GREATER_THEN_EQUAL_TO -> ">="
        Comparison.Type.GREATER_THAN -> ">"
    }

    companion object {
        const val TAB = "    "
    }

}