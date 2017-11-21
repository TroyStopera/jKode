package com.troystopera.jkode.exec

expect class CallStack<E : Executable> {

    val base: String

    val callers: List<String>

    override fun toString(): String

    override fun equals(other: Any?): Boolean

    override fun hashCode(): Int

    companion object {
        fun execToString(executable: Executable): String
        fun callerToString(caller: Caller?): String
    }

}