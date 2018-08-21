package com.troystopera.jkode

import com.troystopera.jkode.exec.Executable
import com.troystopera.jkode.exec.Executor
import com.troystopera.jkode.exec.MutableOutput
import com.troystopera.jkode.exec.Scope

/**
 * Abstract class that is used during execution to stop a certain execution branch.
 *
 * Classes that extend CtrlStmt are designed to be a point in code that stops normal execution and returns to a previous execution stack.
 *
 * @param T the type of data this CtrlStmt wants to pass up the stack after interrupting execution.
 * @property data any object of type [T].
 * @constructor Creates a new instance of CtrlStmt with the given data.
 */
abstract class CtrlStmt<T : Any>(val data: T) : Executable<CtrlStmt<T>>() {

    /**
     * This [Executable] returns itself when executed in order to pass its data up the call stack.
     * @return this CtrlStmt.
     */
    override fun onExecute(scope: Scope, executor: Executor, output: MutableOutput?) = this

}