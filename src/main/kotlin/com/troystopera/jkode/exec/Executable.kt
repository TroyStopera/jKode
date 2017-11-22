package com.troystopera.jkode.exec

import com.troystopera.jkode.exec.override.ExecutionWatcher

abstract class Executable {

    protected abstract fun onExecute(scope: Scope, output: ExecOutput, watcher: ExecutionWatcher?): Any?

    fun execute(scope: Scope, output: ExecOutput, watcher: ExecutionWatcher?): Any? {
        return if (watcher == null) {
            onExecute(scope, output, watcher)
        } else {
            watcher.callStack.startCall(this)
            val result = watcher.getOverride<Executable>(watcher.callStack)?.execute(this, scope, output, watcher)
                    ?: onExecute(scope, output, watcher)
            watcher.callStack.endCall()
            result
        }
    }

}