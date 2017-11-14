package com.troystopera.jkode.exec

import com.troystopera.jkode.Scope
import com.troystopera.jkode.vars.Var

abstract class Executable {

    protected abstract fun execute(scope: Scope, output: Output): Var<*>?

    fun execute(executor: Executor, scope: Scope, output: Output): Var<*>? =
            executor.override(this)?.execute(this, scope, output) ?: execute(scope, output)

}