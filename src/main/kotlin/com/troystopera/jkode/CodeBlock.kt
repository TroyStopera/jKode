package com.troystopera.jkode

import com.troystopera.jkode.exec.ExecOutput
import com.troystopera.jkode.exec.Executable
import com.troystopera.jkode.exec.Executor
import com.troystopera.jkode.exec.Scope
import com.troystopera.jkode.vars.Var

open class CodeBlock : Component() {

    private val executables = mutableListOf<Executable<*>>()

    fun add(executable: Executable<Unit>) = executables.add(executable)

    fun add(component: Component) = executables.add(component)

    fun add(aReturn: Return<*>) = executables.add(aReturn)

    override fun onExecute(scope: Scope, output: ExecOutput?, executor: Executor?): Var<*>? {
        for (e in executables) {
            val v = e.execute(scope, output, executor)
            if (v is Var<*>) return v
        }
        return null
    }

}
