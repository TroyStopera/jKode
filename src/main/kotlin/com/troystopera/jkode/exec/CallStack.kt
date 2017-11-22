package com.troystopera.jkode.exec

abstract class CallStack {

    abstract fun getBase(): String

    abstract fun getCallers(): List<String>

    override fun toString() = getBase() + getCallers().joinToString { "@" + it }

    override fun equals(other: Any?): Boolean {
        if (other !is CallStack) return false
        return toString() == other.toString()
    }

    override fun hashCode() = toString().hashCode()

}