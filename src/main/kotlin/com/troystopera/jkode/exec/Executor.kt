package com.troystopera.jkode.exec

open class Executor {

    open fun execute(executable: Executable): Output {
        val output = ExecOutput()
        val scope = Scope()
        executable.execute(scope, output, null)
        return output
    }

}