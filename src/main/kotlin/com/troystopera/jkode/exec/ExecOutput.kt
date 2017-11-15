package com.troystopera.jkode.exec

import com.troystopera.jkode.Output
import com.troystopera.jkode.vars.Var

class ExecOutput : Output() {

    fun print(string: String) {
        console.add(string)
    }

    fun err(string: String) {
        error.add(string)
    }

    fun setReturn(retVar: Var<*>?) {
        returnVar = retVar
    }

}