package com.troystopera.jkode

import com.troystopera.jkode.exec.Executable
import com.troystopera.jkode.vars.Var
import com.troystopera.jkode.vars.VarType

abstract class Evaluation<out T : Var<*>>(val varType: VarType<T>) : Executable<T>()