package com.troystopera.jkode

import com.troystopera.jkode.exec.ExecOutput
import com.troystopera.jkode.exec.Executable
import com.troystopera.jkode.exec.Scope

open class Executor {

    open fun execute(executable: Executable): Output {
        val output = ExecOutput()
        val scope = Scope()
        executable.execute(scope, output, null, null)
        return output
    }

}