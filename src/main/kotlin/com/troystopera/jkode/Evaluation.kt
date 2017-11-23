package com.troystopera.jkode

import com.troystopera.jkode.exec.Executable
import com.troystopera.jkode.vars.Var

abstract class Evaluation<out T : Var<*>> : Executable<T>()
