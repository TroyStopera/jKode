package com.troystopera.jkode.control

import com.troystopera.jkode.exec.MutableOutput
import com.troystopera.jkode.exec.Executable
import com.troystopera.jkode.exec.Executor
import com.troystopera.jkode.exec.Scope

object Break : Executable<CtrlObject<Unit>>() {

    private val obj = CtrlObject(CtrlType.BREAK, Unit)

    override fun onExecute(scope: Scope, output: MutableOutput?, executor: Executor?): CtrlObject<Unit> = obj

}