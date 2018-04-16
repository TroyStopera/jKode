package com.troystopera.jkode.format

import com.troystopera.jkode.*
import com.troystopera.jkode.components.*
import com.troystopera.jkode.control.Break
import com.troystopera.jkode.control.Continue
import com.troystopera.jkode.control.Return
import com.troystopera.jkode.evaluations.*
import com.troystopera.jkode.JFunction
import com.troystopera.jkode.statements.ArrayAssign
import com.troystopera.jkode.statements.Assignment
import com.troystopera.jkode.statements.Declaration
import com.troystopera.jkode.vars.ArrayType
import com.troystopera.jkode.vars.*
import com.troystopera.jkode.vars.JVar
import com.troystopera.jkode.vars.VarType

object JavaFormat : CodeFormat() {

    override fun formatComponent(component: Component, indent: String): String = when (component) {
        is Conditional -> {
            var string = ""
            val branches = component.branches.iterator()
            if (branches.hasNext())
                string += indent + formatConditionalBranch(branches.next(), indent)
            while (branches.hasNext())
                string += " else ${formatConditionalBranch(branches.next(), indent)}"
            if (component.elseBranch != null)
                string += " else {\n${formatCodeBlock(component.elseBranch!!, indent + TAB)}\n$indent}"
            string + "\n"
        }

        is ForLoop -> "${indent}for (${formatStatement(component.initialization)} " +
                "${formatEvaluation(component.condition)}; " +
                "${formatStatement(component.afterthought).dropLast(1)}) " +
                "{\n${formatCodeBlock(component, indent + TAB)}\n$indent}\n"

        is DoWhileLoop -> "${indent}do {\n" +
                "${formatCodeBlock(component, indent + TAB)}\n" +
                "$indent} while (${formatEvaluation(component.condition)})\n"

        is WhileLoop -> "${indent}while (${formatEvaluation(component.condition)}) {\n" +
                "${formatCodeBlock(component, indent + TAB)}\n$indent}\n"

        is CodeBlock -> formatCodeBlock(component, indent)

        else -> "${indent}Unknown component ${component::class.simpleName}"
    }

    override fun formatControlStmt(ctrlStmt: CtrlStmt<*>, indent: String): String = indent + when (ctrlStmt) {
        is Break -> "break"
        is Continue -> "continue"
        is Return<*> -> "return ${formatEvaluation(ctrlStmt.data)}"
        else -> "Unknown control statement ${ctrlStmt::class.simpleName}"
    } + ";"

    override fun formatEvaluation(evaluation: Evaluation<*>, indent: String): String = indent + when (evaluation) {
        is ArrayAccess<*> -> "${formatEvaluation(evaluation.array)}[${formatEvaluation(evaluation.index)}]"

        is ArrayLength -> "${formatEvaluation(evaluation.array)}.length"

        is Comparison<*> -> formatEvaluation(evaluation.leftValue) +
                " ${formatComparisonType(evaluation.type)} " +
                formatEvaluation(evaluation.rightValue)

        is MathOperation<*, *> -> formatEvaluation(evaluation.leftValue) +
                " ${formatOperationType(evaluation.type)} " +
                formatEvaluation(evaluation.rightValue)

        is JVar<*> -> formatVar(evaluation, indent)

        is JVarEvaluation<*> -> formatVar(evaluation.jVar, indent)

        is Variable<*> -> evaluation.name

        else -> "Unknown Evaluation ${evaluation::class.simpleName}"
    }

    override fun formatFunction(jFunction: JFunction<*>, indent: String): String =
            "${indent}public ${formatVarType(jFunction.returnType)} ${jFunction.name}() {\n" +
                    formatCodeBlock(jFunction.body, indent + TAB) + "\n$indent}"

    override fun formatStatement(statement: Statement, indent: String): String = indent + when (statement) {
        is Assignment -> "${statement.varName} = ${formatEvaluation(statement.value)}"

        is Declaration<*> -> formatVarType(statement.varType) + " ${statement.varName}" +
                if (statement.initialValue != null) " ${formatEvaluation(statement.initialValue)}" else ""

        is ArrayAssign<*> -> "${formatEvaluation(statement.array)}[${formatEvaluation(statement.index)}] = " +
                formatEvaluation(statement.value)

        else -> "Unknown Statement ${statement::class.simpleName}"
    } + ";"

    override fun formatVar(v: JVar<*>?, indent: String): String = indent + when {
        v == null || v.isNull || v is UnitVar -> "null"
        v is IntVar -> v.value
        v is StringVar -> "\"${v.value}\""
        v is BooleanVar -> if (v.value) "true" else "false"
        v is ArrayVar<*> -> {
            var string = "{"
            val elements = v.value.iterator()
            if (elements.hasNext())
                string += formatVar(elements.next())
            while (elements.hasNext())
                string += ", ${formatVar(elements.next())}"
            string + "}"
        }
        else -> "Unknown type ${v::class.simpleName}"
    }

    override fun formatVarType(type: VarType<*>, indent: String): String = indent + when (type) {
        is ArrayType<*> -> formatVarType(type.elementType) + "[]"
        VarType.UNIT -> "void"
        VarType.BOOLEAN -> "boolean"
        VarType.INT -> "int"
        VarType.STRING -> "String"
    }

    private fun formatConditionalBranch(branch: Conditional.Branch, indent: String): String =
            "if (${formatEvaluation(branch.condition)}) {\n" +
                    "${formatCodeBlock(branch, indent + TAB)}\n$indent}"


    private fun formatCodeBlock(codeBlock: CodeBlock, indent: String): String = codeBlock.getExecutables().joinToString(
            separator = "\n", transform = { format(it, indent) })

}