package com.troystopera.jkode

import com.troystopera.jkode.exec.Executable
import com.troystopera.jkode.vars.JVar
import com.troystopera.jkode.vars.VarType

/**
 * Abstract class representing code that returns some variable/value at runtime.
 *
 * These classes are used to evaluate a variable, function, or any other value at runtime in virtual code.
 *
 * @param T the type of [JVar] this Evaluation will be returning when executed.
 * @property varType the [VarType] of the variable that this Evaluation will return.
 * @constructor Creates a new Evaluation that will provide a variable of type varType at virtual runtime.
 */
abstract class Evaluation<out T : JVar<*>>(val varType: VarType<T>) : Executable<T>()