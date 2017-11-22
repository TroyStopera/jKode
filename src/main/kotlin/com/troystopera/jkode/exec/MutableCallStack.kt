package com.troystopera.jkode.exec

import com.troystopera.jkode.exec.override.Watchable

class MutableCallStack : CallStack() {

    private var base = ""
    private val callers = mutableListOf<String>()

    override fun getBase() = base

    override fun getCallers() = callers

    fun startCall(executable: Executable) {
        if (base.isNotEmpty()) callers.add(0, base)
        base = Watchable.execToString(executable)
    }

    fun endCall() {
        base = callers.removeAt(0)
    }

}