package com.troystopera.jkode.components

import com.troystopera.jkode.Component
import com.troystopera.jkode.CtrlStmt
import com.troystopera.jkode.Evaluation
import com.troystopera.jkode.exec.Executor
import com.troystopera.jkode.exec.MutableOutput
import com.troystopera.jkode.exec.Scope
import com.troystopera.jkode.vars.BooleanVar

class Conditional private constructor(
        val branches: List<Branch>,
        val elseBranch: Branch?
) : Component() {

    override fun onExecute(scope: Scope, output: MutableOutput?, executor: Executor?): CtrlStmt<*>? {
        val branchTaken = branches.firstOrNull { it.condition.execute(scope, output, executor).value }
        return branchTaken?.execute(scope, output, executor) ?: elseBranch?.execute(scope, output, executor)
    }

    class Builder {

        private val branches = mutableListOf<Branch>()
        private var elseBranch: Branch? = null

        fun addBranch(branch: Branch) {
            branches.add(branch)
        }

        fun setElseBlock(codeBlock: CodeBlock?) {
            elseBranch = if (codeBlock != null) Branch(BooleanVar.TRUE.asEval(), codeBlock)
            else null
        }

        fun build() = Conditional(branches, elseBranch)

    }

    data class Branch(val condition: Evaluation<BooleanVar>) : CodeBlock() {

        internal constructor(condition: Evaluation<BooleanVar>, codeBlock: CodeBlock) : this(condition) {
            codeBlock.getExecutables().forEach { add(it) }
        }

    }

}