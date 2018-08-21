package com.troystopera.jkode

import com.troystopera.jkode.exec.*

/**
 * This object represents a blank line in a program.
 *
 * This singleton object does nothing during execution, but can be used to add a blank line to generated code.
 */
object BlankLine : Executable<Unit>() {

    /**
     * Does nothing, and returns nothing.
     * @return [Unit]
     */
    override fun onExecute(scope: Scope, executor: Executor, output: MutableOutput?) = Unit

}