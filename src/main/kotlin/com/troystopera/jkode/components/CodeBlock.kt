package com.troystopera.jkode.components

import com.troystopera.jkode.Component
import com.troystopera.jkode.CtrlStmt
import com.troystopera.jkode.exec.MutableOutput
import com.troystopera.jkode.exec.Executable
import com.troystopera.jkode.exec.Executor
import com.troystopera.jkode.exec.Scope

abstract class CodeBlock : Component() {

    private val executables = mutableListOf<Executable<*>>()

    fun getExecutables() = executables.toList()

    fun add(executable: Executable<*>) = executables.add(executable)

    fun executeBody(scope: Scope, output: MutableOutput?, executor: Executor?): CtrlStmt<*>? {
        executables.forEach {
            val v = it.execute(scope, output, executor)
            if (v is CtrlStmt<*>) return v
        }
        return null
    }

}
