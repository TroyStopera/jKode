package com.troystopera.jkode

import com.troystopera.jkode.exec.Executable
import com.troystopera.jkode.exec.Executor
import com.troystopera.jkode.exec.MutableOutput
import com.troystopera.jkode.exec.Scope

abstract class CtrlStmt<T : Any>(val data: T) : Executable<CtrlStmt<T>>() {

    override fun onExecute(scope: Scope, output: MutableOutput?, executor: Executor?) = this

}