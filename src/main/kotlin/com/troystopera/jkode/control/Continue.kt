package com.troystopera.jkode.control

import com.troystopera.jkode.exec.ExecOutput
import com.troystopera.jkode.exec.Executable
import com.troystopera.jkode.exec.Executor
import com.troystopera.jkode.exec.Scope

object Continue : Executable<CtrlObject<Unit>>() {

    private val obj = CtrlObject(CtrlType.CONTINUE, Unit)

    override fun onExecute(scope: Scope, output: ExecOutput?, executor: Executor?): CtrlObject<Unit> = obj

}