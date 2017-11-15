package com.troystopera.jkode.exec

import com.troystopera.jkode.vars.Var

abstract class Executable {

    protected abstract fun onExecute(scope: Scope, output: ExecOutput, watcher: ExecutionWatcher?): Var<*>?

    fun execute(scope: Scope, output: ExecOutput, watcher: ExecutionWatcher?): Var<*>? =
            watcher?.override(this)?.execute(this, scope, output, watcher)
                    ?: onExecute(scope, output, watcher)

}