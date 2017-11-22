package com.troystopera.jkode.exec.override

import com.troystopera.jkode.exec.CallStack
import com.troystopera.jkode.exec.Executable
import com.troystopera.jkode.exec.MutableCallStack

interface ExecutionWatcher {

    val callStack: MutableCallStack

    fun <E : Executable> getOverride(callStack: CallStack): ExecutionOverride<E>?

}