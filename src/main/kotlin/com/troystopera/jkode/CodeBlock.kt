package com.troystopera.jkode

import com.troystopera.jkode.control.CtrlObject
import com.troystopera.jkode.control.Return
import com.troystopera.jkode.exec.ExecOutput
import com.troystopera.jkode.exec.Executable
import com.troystopera.jkode.exec.Executor
import com.troystopera.jkode.exec.Scope
import com.troystopera.jkode.vars.Var

open class CodeBlock : Component() {

    private val executables = mutableListOf<Executable<*>>()

    fun add(executable: Executable<*>) = executables.add(executable)

    override fun onExecute(scope: Scope, output: ExecOutput?, executor: Executor?): CtrlObject<*>? {
        for (e in executables) {
            val v = e.execute(scope, output, executor)
            if (v is CtrlObject<*>) return v
        }
        return null
    }

}
