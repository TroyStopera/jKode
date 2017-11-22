package com.troystopera.jkode.exec.override

import com.troystopera.jkode.exec.ExecOutput
import com.troystopera.jkode.exec.Executable
import com.troystopera.jkode.exec.Scope
import com.troystopera.jkode.vars.Var

interface ExecutionOverride<in E : Executable> {

    fun execute(executable: E, scope: Scope, output: ExecOutput, watcher: ExecutionWatcher?): Var<*>?

}