package com.troystopera.jkode.exec

import com.troystopera.jkode.vars.Var

abstract class Executable {

    protected abstract fun onExecute(scope: Scope, output: ExecOutput, caller: Caller, watcher: ExecutionWatcher?): Var<*>?

    fun execute(scope: Scope, output: ExecOutput, caller: Caller?, watcher: ExecutionWatcher?): Var<*>? =
            watcher?.getOverride(this, caller)?.execute(this, scope, output, watcher)
                    ?: onExecute(scope, output, Caller(caller, this), watcher)

}