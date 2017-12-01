package com.troystopera.jkode.components

import com.troystopera.jkode.Component
import com.troystopera.jkode.CtrlStmt
import com.troystopera.jkode.Evaluation
import com.troystopera.jkode.exec.Executor
import com.troystopera.jkode.exec.MutableOutput
import com.troystopera.jkode.exec.Scope
import com.troystopera.jkode.vars.BooleanVar

class Conditional private constructor(
        val branches: Array<Branch>,
        val elseBlock: CodeBlock?
) : Component() {

    override fun onExecute(scope: Scope, output: MutableOutput?, executor: Executor?): CtrlStmt<*>? {
        val branchTaken = branches.firstOrNull { it.condition.execute(scope, output, executor).value }
        return branchTaken?.codeBlock?.execute(scope, output, executor) ?: elseBlock?.execute(scope, output, executor)
    }

    class Builder(condition: Evaluation<BooleanVar>, codeBlock: CodeBlock) {

        private val branches = mutableListOf<Branch>()
        private var elseBlock: CodeBlock? = null

        init {
            branches.add(Branch(condition, codeBlock))
        }

        fun addBranch(condition: Evaluation<BooleanVar>, codeBlock: CodeBlock) {
            branches.add(Branch(condition, codeBlock))
        }

        fun addBranch(branch: Branch) {
            branches.add(branch)
        }

        fun setElseBlock(codeBlock: CodeBlock?) {
            elseBlock = codeBlock
        }

        fun build() = Conditional(branches.toTypedArray(), elseBlock)

    }

    data class Branch(
            val condition: Evaluation<BooleanVar>,
            val codeBlock: CodeBlock
    )

}