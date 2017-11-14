package com.troystopera.jkode.exec

import com.troystopera.jkode.Scope
import com.troystopera.jkode.vars.Var

interface ExecutionOverride<in E : Executable> {

    fun execute(executable: E, scope: Scope, output: Output): Var<*>?

}