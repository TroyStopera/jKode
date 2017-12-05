package com.troystopera.jkode.control

import com.troystopera.jkode.CtrlStmt
import com.troystopera.jkode.Evaluation
import com.troystopera.jkode.vars.JVar

class Return<T : JVar<*>>(
        evaluation: Evaluation<T>
) : CtrlStmt<Evaluation<T>>(evaluation)