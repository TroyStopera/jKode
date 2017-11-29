package com.troystopera.jkode.components

import com.troystopera.jkode.Component
import com.troystopera.jkode.control.CtrlObject
import com.troystopera.jkode.exec.MutableOutput
import com.troystopera.jkode.exec.Executable
import com.troystopera.jkode.exec.Executor
import com.troystopera.jkode.exec.Scope

open class CodeBlock : Component() {

    private val executables = mutableListOf<Executable<*>>()

    fun add(executable: Executable<*>) = executables.add(executable)

    override fun onExecute(scope: Scope, output: MutableOutput?, executor: Executor?): CtrlObject<*>? {
        for (e in executables) {
            val v = e.execute(scope, output, executor)
            if (v is CtrlObject<*>) return v
        }
        return null
    }

}
