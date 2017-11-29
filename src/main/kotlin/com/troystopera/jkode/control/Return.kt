package com.troystopera.jkode.control

import com.troystopera.jkode.CtrlStmt
import com.troystopera.jkode.Evaluation
import com.troystopera.jkode.exec.MutableOutput
import com.troystopera.jkode.exec.Executable
import com.troystopera.jkode.exec.Executor
import com.troystopera.jkode.exec.Scope
import com.troystopera.jkode.vars.Var

class Return<T : Var<*>>(
        evaluation: Evaluation<T>
) : CtrlStmt<Evaluation<T>>(evaluation)