package com.troystopera.jkode.exec.override

import com.troystopera.jkode.exec.ExecOutput
import com.troystopera.jkode.exec.Executable
import com.troystopera.jkode.exec.Executor
import com.troystopera.jkode.exec.Scope

interface ExecutionOverride<out T : Any?, in E : Executable<T>> {

    fun execute(executable: E, scope: Scope, output: ExecOutput?, executor: Executor?): T

}