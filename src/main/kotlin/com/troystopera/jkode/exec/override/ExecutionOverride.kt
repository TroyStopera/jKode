package com.troystopera.jkode.exec.override

import com.troystopera.jkode.exec.MutableOutput
import com.troystopera.jkode.exec.Executable
import com.troystopera.jkode.exec.Executor
import com.troystopera.jkode.exec.Scope

interface ExecutionOverride<out T : Any?, in E : Executable<T>> {

    fun execute(executable: E, scope: Scope, output: MutableOutput?, executor: Executor?): T

}