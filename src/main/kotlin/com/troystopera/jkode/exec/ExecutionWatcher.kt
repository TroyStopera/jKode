package com.troystopera.jkode.exec

interface ExecutionWatcher {

    fun <E : Executable> getOverride(executable: E, caller: Caller?): ExecutionOverride<E>?

}