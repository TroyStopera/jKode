package com.troystopera.jkode.components

import com.troystopera.jkode.Component
import com.troystopera.jkode.CtrlStmt
import com.troystopera.jkode.Evaluation
import com.troystopera.jkode.exec.Executor
import com.troystopera.jkode.exec.MutableOutput
import com.troystopera.jkode.exec.Scope
import com.troystopera.jkode.vars.BooleanVar

class Conditional private constructor(
        val branches: MutableList<Branch>,
        var elseBranch: ElseBranch?
) : Component() {

    override fun onExecute(scope: Scope, executor: Executor, output: MutableOutput?): CtrlStmt<*>? {
        val branchTaken = branches.firstOrNull { it.condition.execute(scope, executor, output).value }
        return branchTaken?.body?.execute(scope, executor, output) ?: elseBranch?.body?.execute(scope, executor, output)
    }

    class Builder {

        private val branches = mutableListOf<Branch>()
        private var elseBranch: ElseBranch? = null

        fun addBranch(branch: Branch): Builder {
            branches.add(branch)
            return this
        }

        fun setElse(elseBranch: ElseBranch?): Builder {
            this.elseBranch = elseBranch
            return this
        }

        fun build() = Conditional(branches, elseBranch)

    }

    class Branch(var condition: Evaluation<BooleanVar>) {
        val body = object : CodeBlock() {
            override fun onExecute(scope: Scope, executor: Executor, output: MutableOutput?) = executeBody(scope, executor, output)
        }
    }

    class ElseBranch {
        val body = object : CodeBlock() {
            override fun onExecute(scope: Scope, executor: Executor, output: MutableOutput?) = executeBody(scope, executor, output)
        }
    }

}