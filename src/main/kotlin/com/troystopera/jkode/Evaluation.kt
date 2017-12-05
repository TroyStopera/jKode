package com.troystopera.jkode

import com.troystopera.jkode.exec.Executable
import com.troystopera.jkode.vars.JVar
import com.troystopera.jkode.vars.VarType

abstract class Evaluation<out T : JVar<*>>(val varType: VarType<T>) : Executable<T>()