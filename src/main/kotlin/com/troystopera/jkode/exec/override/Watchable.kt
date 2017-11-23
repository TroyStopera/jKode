package com.troystopera.jkode.exec.override

import com.troystopera.jkode.exec.CallStack
import com.troystopera.jkode.exec.Executable

expect class Watchable<E : Executable<*>> : CallStack {

    override fun getBase(): String

    override fun getCallers(): List<String>

    companion object {
        fun execToString(executable: Executable<*>): String
    }

}