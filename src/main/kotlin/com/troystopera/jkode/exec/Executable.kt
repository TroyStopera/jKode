package com.troystopera.jkode.exec

abstract class Executable<out T : Any?> {

    protected abstract fun onExecute(scope: Scope, output: ExecOutput?, executor: Executor?): T

    fun execute(scope: Scope, output: ExecOutput? = null, executor: Executor? = null): T {
        val result = executor?.onPreExecute(this)?.execute(this, scope, output, executor) ?:
                onExecute(scope, output, executor)
        executor?.onPostExecute(this, result)
        return result
    }

}