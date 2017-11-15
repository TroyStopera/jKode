package com.troystopera.jkode

import com.troystopera.jkode.vars.Var

open class Output {

    protected val console = mutableListOf<String>()
    protected val error = mutableListOf<String>()
    protected var returnVar: Var<*>? = null

    fun getConsole(): List<String> = console.toList()

    fun getErrors(): List<String> = error.toList()

    fun getReturn(): Var<*>? = returnVar

}