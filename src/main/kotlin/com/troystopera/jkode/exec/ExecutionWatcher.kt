package com.troystopera.jkode.exec

interface ExecutionWatcher {

    fun <E : Executable> override(executable: E): ExecutionOverride<E>?

}