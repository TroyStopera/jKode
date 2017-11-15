package com.troystopera.jkode

import com.troystopera.jkode.exec.Executable
import com.troystopera.jkode.exec.ExecutionOverride
import com.troystopera.jkode.exec.ExecutionWatcher

class Executor : ExecutionWatcher {

    override fun <E : Executable> override(executable: E): ExecutionOverride<E>? {
        TODO()
    }

}